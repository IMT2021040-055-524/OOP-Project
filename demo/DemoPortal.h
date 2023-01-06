#ifndef DEMOPORTAL_H
#define DEMOPORTAL_H
#include<unordered_map>
#include<vector>
#include "../ecomm/Portal.h"
using namespace std;

class DemoPortal : public Portal{
    private:
	int requestID;
    string portalID;
    unordered_map<int,vector<string> > mapping; //To map the requestID to the seller
    unordered_map<int,bool>processedResponses; //To mark the processed responses to prevent printing it for a second time during consecutive checks
    public:
        DemoPortal(string portalID); //Constructor
        void processUserCommand(string command);
        void checkResponse();
};

#endif 
