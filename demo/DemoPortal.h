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
    unordered_map<int,vector<string> > mapping;
    unordered_map<int,bool>processedResponses;
    public:
        DemoPortal(string portalID);
        void processUserCommand(string command);
        void checkResponse();
};

#endif 
