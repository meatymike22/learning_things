# Identity management Access control

## ID

* claimed Identity
* public info
* based on first and last name
* username
* user account
* employee number
* there is no secret/proof

## Authentication

* Verifying the claimant
* 3 main types
  * something you know - pin, password
  * something you have - one time pin
  * something you are - biometrics
  * something you do - keyboard typing
  * somewhere you are - geolocation
* single factor
* two factor authentication
  * stronger
  * two of above types

## AAA

You do AAA locally often when:

* you create new user in Windows/Linux (authenticate)
  * you change that user from Admin to User (authorization)
  * system logs the changes to hard drive (accounting)

* On your home router
  * you create a new user or change admin PW (authen)
  * create a new user w admin or user priv (author)
  * system logs those changes (accounting) 

Wouldnt it be nice to have one admin user and one standard user and manage everything
from one central location

### AAA Servers

* Windows active directory and radius are most popular
* log onto domain (authen)
* make changes to permissions (author)
* logs changes (accounting) 

### Multiple Services/Methods/protocols

* TACACS+ - remote AAA on cisco equipment
* Radius - open source, most popular for networking
* Kerberos - open protocol for passing permission tokens for SSO. AD uses
* X.500 - hierarchy directory structure ideal for organizations. AD uses
* LDAP - service and a protocol for querying x.500 type data structures
         and passing on AAA instructions. Also allows to query a windows AD
         server to authenticate approved user. Can talk b/t Kerb & Radius

### Radius - Remote Authentication Dial In User Service

* Instead of creating and storing separate usernames and passwords for each device 
separately, we can use Radius protocols and a Radius server to:
  * allow access to the network
  * create/modify/delete users&permissions once
* the radius server centralizes the users and their passwords (authen) in a database with 
the permissions devices that user has (author) and log each logon & changes (accounting)

* Advantages
  * ideal for remote access authentication
  * one database holds all credentials
  * any radius capable device can query one location and get current user authen

* Disadvantages
  * devices must be radius capable
  * have to type your login after every session
  * your creds dont follow you around like in a Windows domain (SSO)

AD/Kerb

Kerberos

* authenticate once, trusted by system
* no need to re-authenticate
* mutual authentication - client and server. protects against MITM
* compatible with all OSs

* uses SSO for all permissions
* after the SSO the ticket will be valid for a period and allow direct access to other 
servers/shares and services that the user is entitled to without user having to sign on
to them specifically 

LDAP

* LDAP (port 389), secure LDAP (port 636)
* AD stores permissions in x.500 format
* LDAP can interface with this existing database stdcutre and control Radius enabled devices
* Some devices are not managed by an active ditectory domain - ie switches, routers, and users
  would normally need to get centralized authentication from a Radius server
* However, an LDAP server can connect to an AD Domain controller and so LDAP works with 
  Microsoft AD to negate the need for a separate database

## 3rd party Web Authentication

* Shibboleth - generally web based SSO for apps where a federation of service/App providers
create a shared Identity Provider(IdP) which provides the Authentication for the server
providers
* generally certs are required as part od the IdP infrastructure

* SAML
  * comms based on xml language
  * authen is via a signed XML doc
* OpenID connect
  * author is via id_token which is a signed JSON doc
* OAUTH
  * older framework - no specific token defined

Example: When you log into a website FROM another company (logging into some random site 
using your Facebook creds) this is what happens ^^^^

## Authentication using Point to Point Protocols

* PAP
  * older - obfuscation to hide password but no encryption
* Chap
  * challenge handshake authentication protocol
  * more secure - hashing of password with a challenge (salt/IV)
  * initial and ongoing verification of client identity
* MSCHAP
  * microsoft PPTP (PTP tunneling protocol)
  * MSCHAPv2 is more secure than CHAP

### 802.1x / EAP

* Extensible Authentication Protocol (framework)
* allows us to authenticate remotely via a switch
* 3 roles
  * supplicant - software on client
  * authenticator - L2 devices that passes on request
  * AAA server authenticates and authorizes

* EAP - FAST - can just use passwords
  * flexible authentication via secure tunneling
  * no cert required, uses PAC (protected access credential) which can be managed
    dynamically by the authentication server. 
* EAP - TLS
  * both client and AAA server must have certs and can then dynamically create the
    session key
* EAP - TTLS (Tunneling TLS)
  * Only the AAA server is absolutely required to have a cert

* EAP FAST and LEAP - only passwords required
* EAP TTLS and PEAP - cert only required on server side
* EAP TLS - cert required both client and server


## WPA 

* SSID cannot be blank. required. but dont transmit to be safe
* best channel on 2.4 Ghz? 1,6,11. Set 2 APs for little interference

* Other protocols
  * WEP - not secure
  * WPS - Wireless Protected setup
  * WPA TKIP
  * WPA2 ? AES CCMP and TKIP?
    * TKIP supports older devices
    * this is the most secure implementation other than WPA3
  * WPA3 with SAE
    * Simultaneous Authentication of equals

* When should we use Open ( no PW) vs PSK (pre-shared key) vs SAE vs Enterprise (Radius/Kerb)
* When should we use MAC Address whitelisting? never
* What is a captive portal? public wifi, agreeing to terms and conditions

## Implementation of identity and access controls

### Access control models

* MAC - Mandatory access control
  * strict rules set by SYS Admin
  * everything is an object - user, file, server - permissions must line up
* DAC - discretionary access control
  * Owners assign trust - like creator of file in Windows or software developer gives
    permission to team
* Rule BAC - Rule Based Access Control
  * Can filter not just by identity, but time of day, size of file
* Role BAC - role based
  * for example all admins, or accountancy or author can modify or delete
  * only network admins can delete, everyone can see, network admins can modify
* attribute based - mandatory + rule based - most strict - newest!

### Biometrics

* fingerprints, retinal, voice, facial
* how good is the system
  * false acceptance rate
  * false rejection rate
  * crossover error rate/EER
    * the metric to judge the system

### Physical access control

* crypto key or digi cert
* memory card
* smart card with pin
* magnetic cards
* proximity/RFID card
* locks/gates
* biometric readers

### Physical Access Prevention

* ID badges
* Smart cards w/ pin
* Key fob

### Tokens

* something you have - not something you know
  * asynchronous
  * synchronous
* HOTP (event based)
* TOTP (time based - clock always running)
* hardware based
* software based


## NTFS Windows File/folder permissions

* Full control - change permissions, change ownership
* modify/delete - combines read& execute with write and allows deletion of anything
* read&execute - read + list folder contents + run executables
* list folder contents - view contents of directory and access subdirectories
* read - see ownership, view anything
* write - allows user to create new entities

## Linux permissions

* rwx means read, write, execute

* d rwx rwx rwx 2 root root 4096 Jan 9 2016 Desktop
  1. 2.  3.  4.

1. directory attribute - d = dir or . = file
2. owner
3. group
4. world

* drwxr-xr-x 2 andrew andrew 4096 Apr 11 16:02 Desktop
       3.          1.    2.

1. owner
2. group
3. Owner has full control, group and world has read and execute, but no write

### Chmod - setting/modifying permissions linux
|-----|-----|-----|
| 2^2 | 2^1 | 2^0 |
| r   | w   | x   |

rwx = 7
r-x = 5

chmod 644 test.txt

* hundred's digit is owner, tens is group, ones is world

### Chown - linux - change ownership

* -rw-rw-r-- 1 root grouptest 532 Mar 7 21:38 sample
* sudo chown test sample
* -rw-rw-r-- 1 test grouptest 532 Mar 7 21:38 sample

## Account types

* privileged/admin
* standard - may be part of a group in group policy
* guest - no longer used, cant save data
* service account - a program or service needs to interact with another
  * needs permissions not tied to a user
  * a web app will usually have a service account with the database server

## Network policies

* Acceptable use policies
  * appropriate use of a orgs resources/secrets
* trusted/untrusted software sources
  * install software only from trusted sources and properly licensed
* mobile device management (MDM)
  * controlling data on mobile devices, often used with employee's personal devices
    partition and control/wipe of company data
* principle of least privilege - __should be enforced throughout__
  * assign the least amount of priv to get the job done