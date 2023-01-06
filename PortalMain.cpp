#include "./demo/DemoPortal.h"
#include<iostream>
#include<string>
using namespace std;
int main(int argc, char* argv[]){
    string portalId=argv[1]; // To take the portalID as an argument 
    DemoPortal* p = new DemoPortal(portalId);
    string data;
    while(true){
        getline(cin,data);
        if(data=="End"){
            break;
        }
        p->processUserCommand(data);
    }
}


