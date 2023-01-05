#ifndef PORTAL_H
#define PORTAL_H
#include<string>
using namespace std;
class Portal {
	// Invoked by main or driver class
	// sends command to Platform (by writing to PortalToPlatform)
	// Each command line in the file should have a PortalID as the first token 
	// and a RequestID as second token. 
	// PortalID is unique to each instance of Portal
	// Each request from a portal should have a unique ID
	public:
        virtual void processUserCommand(string)=0;
        
        // checks for pending responses (in PortalToPlatform)
        // Displays response
        virtual void checkResponse()=0;

};
#endif
