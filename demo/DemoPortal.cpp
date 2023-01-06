#include<iostream>
#include<string>
#include<vector>
#include<fstream>
#include<algorithm>
using namespace std;
#include "DemoPortal.h"

void printMatrix(vector<vector<string> > &products){
    for(int i=0;i<products.size();i++){
        for(int j=0;j<products[i].size();j++){
            cout<<products[i][j]<<" ";
        }
        cout<<endl;
    }
}

bool sortName(const vector<string>& v1, const vector<string>& v2){
    return v1[2] < v2[2];
}

bool sortPrice(const vector<string>& v1, const vector<string>& v2){
    return stof(v1[4]) < stof(v2[4]);
}

vector<string>split(string s1){
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

DemoPortal::DemoPortal(string portalID){
    requestID=0;
    this->portalID=portalID;
}

void DemoPortal::processUserCommand(string command){
    fstream f;
    f.open("PortalToPlatform.txt", ios::app);
    requestID++;
    vector<string> split_str = split(command);
    mapping[requestID]=split_str;
    if(split_str[0]=="List"){
        f<<this->portalID<<" "<<this->requestID <<" "<<"List"<<" "<<split_str[1]<<"\n";
    }else if(split_str[0]=="Buy"){
        f<<this->portalID<<" "<<this->requestID<<" "<<"Buy"<<" "<<split_str[1]<<" "<<split_str[2]<<"\n";
    }else if(split_str[0]=="Start"){
        f<<this->portalID<<" "<<this->requestID<<" "<<"Start"<<endl;
    }else if(split_str[0]=="Check"){
        this->checkResponse();
    }else{
        cout<<"Invalid Request.. Didn't Process."<<endl;
        mapping.erase(requestID);
        requestID--;
    }
    f.close();
}

void DemoPortal::checkResponse(){
    string text;
    ifstream MyReadFile("PlatformToPortal.txt");
    if(!MyReadFile){
        return;
    }else if(MyReadFile.peek()==EOF){
        MyReadFile.close();
        return ;
    }
    bool new_output=false;
    vector<vector<string> >products;
    while(getline(MyReadFile, text)){
        vector<string> response = split(text);
        //response[1] is the requestID
        //mapping[stoi(response[1])][0] will be the type of request
        if(processedResponses.find(stoi(response[1]))==processedResponses.end() && this->portalID==(response[0])){
            new_output=true;
            if(mapping[stoi(response[1])][0]!="List"){
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
                    products.clear();
                }
                cout<<text<<endl;
            }else{
                if(products.size()==0){
                    products.push_back(response);
                }else{
                    if(stoi(response[1])==stoi(products[0][1])){
                        products.push_back(response);
                    }else{
                        if(mapping[stoi(products[0][1])][2]=="Name"){
                            sort(products.begin(), products.end(), sortName);
                        }else if(mapping[stoi(products[0][1])][2]=="Price"){
                            sort(products.begin(), products.end(), sortPrice);       
                        }
                        printMatrix(products);
                        products.clear();
                        products.push_back(response);
                    }
                }
                
            }
        }
    }
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
    if(new_output==true){
        for(int i=1;i<=requestID;i++){
            processedResponses[i]=true;
        }
    }
    MyReadFile.close();
}