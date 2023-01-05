#include "./demo/DemoPortal.h"
#include<iostream>
#include<string>
using namespace std;
int main(int argc, char* argv[]){
    string portalId=argv[1];
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

//Start List Buy List Check

