// Written By Vikas K IMT2021040
#include<iostream>
#include<string>
#include<vector>
#include<fstream>
#include<algorithm>
using namespace std;
#include "DemoPortal.h"

void printMatrix(vector<vector<string> > &products){ //Method to print A 2-D array of strings
    for(int i=0;i<products.size();i++){
        for(int j=0;j<products[i].size();j++){
            cout<<products[i][j]<<" ";
        }
        cout<<endl;
    }
}

bool sortName(const vector<string>& v1, const vector<string>& v2){ //Comparator To sort based on Name
    return v1[2] < v2[2];
}

bool sortPrice(const vector<string>& v1, const vector<string>& v2){ //Comparator To sort based on Price
    return stof(v1[4]) < stof(v2[4]);
}

vector<string>split(string s1){ //Splitting a function on spaces and returning vector of individual words
    vector <string> v1;
    int l=0;
    for(int i=0;i<s1.length();i++){
        if(s1[i]==' '){
            v1.push_back(s1.substr(l,i-l));
            l = i + 1;
        }
    }
    v1.push_back(s1.substr(l,s1.length() - l));
    return(v1);
}

DemoPortal::DemoPortal(string portalID){ //Constructor of DemoPortal to initialize first requestID and the portal ID which is passed from PortalMain
    requestID=0;
    this->portalID=portalID;
}

void DemoPortal::processUserCommand(string command){
    fstream f;
    f.open("PortalToPlatform.txt", ios::app); //Opening File in Append Mode To write the Customer Requests
    requestID++; //First RequestID will be 1
    vector<string> split_str = split(command); //Splitting the request
    mapping[requestID]=split_str; //Mapping the requestID to the request for future reference
    if(split_str[0]=="List"){ //Writing Appropriate Command to PortalToPlatform.txt based on request
        f<<this->portalID<<" "<<this->requestID <<" "<<"List"<<" "<<split_str[1]<<"\n";
    }else if(split_str[0]=="Buy"){
        f<<this->portalID<<" "<<this->requestID<<" "<<"Buy"<<" "<<split_str[1]<<" "<<split_str[2]<<"\n";
    }else if(split_str[0]=="Start"){
        f<<this->portalID<<" "<<this->requestID<<" "<<"Start"<<endl;
    }else if(split_str[0]=="Check"){
        this->checkResponse();
    }else{
        cout<<"Invalid Request.. Didn't Process."<<endl; //Invalid Request Type
        mapping.erase(requestID); // As The requestID was already mapped before to the wrong request 
        requestID--; //Invalid request had come
    }
    f.close();
}

void DemoPortal::checkResponse(){
    string text;
    ifstream MyReadFile("PlatformToPortal.txt");
    if(!MyReadFile){ //File Doesn't Exist
        return;
    }else if(MyReadFile.peek()==EOF){ //File is empty
        MyReadFile.close();
        return ;
    }
    bool new_output=false; // Variable that will be used for checking premature 'Check' Command in Portal
    vector<vector<string> >products; // The products that need to be sorted after 'List' Command in Portal
    while(getline(MyReadFile, text)){
        vector<string> response = split(text); //Splitting the response that was read from PlatformToPortal.txt 
        //response[1] is the requestID
        //mapping[stoi(response[1])][0] will be the type of request
        if(processedResponses.find(stoi(response[1]))==processedResponses.end() && this->portalID==(response[0])){ //Checking if this response has been processed before and checking if the response is for this portal
            new_output=true; // We have received new Output so The 'Check' was not premature
            if(mapping[stoi(response[1])][0]!="List"){ 
                //As we did not receive a List, we are sorting and printing the products array if it is non empty
                if(products.size()!=0){
                    //products[0][1] is RequestID
                    if(mapping[stoi(products[0][1])].size()>2){ //Checking if sortOrder was present
                        if(mapping[stoi(products[0][1])][2]=="Name"){ //Sort based on Name
                            sort(products.begin(), products.end(), sortName);
                        }else if(mapping[stoi(products[0][1])][2]=="Price"){ //Sort based on Price
                            sort(products.begin(), products.end(), sortPrice);       
                        }
                    }

                    printMatrix(products); //Printing Sorted Products List
                    products.clear(); // To process new List Command when it comes
                }
                cout<<text<<endl; //Printing The current response
            }else{
                // As we are sorting all products together after a Non List Command comes
                // We need to push the new product to the vector only if it is part of the same requestID
                if(products.size()==0){
                    products.push_back(response);
                }else{
                    if(stoi(response[1])==stoi(products[0][1])){ //Checking if the products in the vector currently are of the same requestID as the current product
                        products.push_back(response);
                    }else{
                        //We have received consecutive List Commands
                        if(mapping[stoi(products[0][1])].size()>2){ //Checking if sortOrder was present
                            if(mapping[stoi(products[0][1])][2]=="Name"){ //Sort based on Name
                                sort(products.begin(), products.end(), sortName);
                            }else if(mapping[stoi(products[0][1])][2]=="Price"){ //Sort based on Price
                                sort(products.begin(), products.end(), sortPrice);       
                            }
                        }
                        printMatrix(products);
                        products.clear();
                        products.push_back(response); //Adding the first response the new List
                    }
                }
                
            }
        }
    }
    //If the final Request was a List
    if(products.size()!=0){
        //products[0][1] is RequestID
        if(mapping[stoi(products[0][1])].size()>2){
            if(mapping[stoi(products[0][1])][2]=="Name"){
                sort(products.begin(), products.end(), sortName);
            }else if(mapping[stoi(products[0][1])][2]=="Price"){
                sort(products.begin(), products.end(), sortPrice);       
            }
        }
        printMatrix(products);
    }
    if(new_output==true){ //If we have received new output, we invalidate all previous requestIDs to prevent outputting previous responses on the terminal
        for(int i=1;i<=requestID;i++){
            processedResponses[i]=true;
        }
    }
    MyReadFile.close();
}
