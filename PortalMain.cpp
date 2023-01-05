#include "./demo/DemoPortal.h"
#include<iostream>
using namespace std;
int main(){
    DemoPortal* p = new DemoPortal("portal-5");
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

