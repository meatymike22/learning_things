# Security Domain and Risk management

## Agreement types

* BPA - business partner agreement
* _SLA - service level agreement - measureable statistics such as bandwidth or recovery times_
* more but not important

## personnel mgmt

* mandatory vacations - no single point of failure
* job rotation - no single point of failure
* sep of duties - protects against fraud
* clean desk - information security
* background checks - history of crime, industrial espionage
* exit interviews - devices and ata retunred, reminder of NDA (non disclosure agreement), 
                    contractual obligations post employment

## Role-based awareness training

* data owner
  * executive with overall reponsibility 
  * must be informed in case of breach

* sys admin (custodian)
  * day-to-day tasks and implementations
* privacy officer
* system owner
* privileged user
* exec user
* NDA

## business impact analysis (BIA)

* helps identify and prioritze info sysmtes
* determine mission/business functions
* determine recovery crtiicality
* idenitfy resource reqs
* idenitify recovery prioerities for sys resources
* allow you to research other disasters
  * learn from others' mistakes
* _if something hurts us how can we get operations back up_

### business impact concept analysis

* RTO - recovery time obj - if something breaks, how long until it works
* RPO - recovery point obj - what amount of time is okay before detrimental
  * backing up computer every 10 min because thats how long we are willing
    to allow data loss
* MTBF - mean time between failures
* MTTR - mean time to repair
* MTD - maximum tolerable downtime

## functions for supporting policies

* standards - binding
* baselines - binding
* procedures - binding
* guidelines - non-binding

* defining mission-essential fucntions
  * why org is there
  * what service is provided
* ID of critical systems
  * including suppliers, staff, required infra
  * supply chain dependencies and single points of failure

## single points of failure

* determine and avoid these
* network redundancy
  * two ISPs
* redundant server for critical services
  * fail over
  * load balancing
  * clustering
* spare equipment onsite
  * router, switches, hard drives

## threat assessments

* environmental - not just direct weather threats, power cuts, escaping toxic materials
* manmade - accidental or malicious, deleting data, downloading malware, logic bomb
* internal vs external - inside/outside org
  * I: Evil Twin AP(access point), ARP poisoning, wireshark
  * E: DDOS, active hacking

## risk assessment and measurements

* SLE = Single loss expectancy - $
* ALE = Annual loss expectancy - $/year
* ARO = Annual rate of occurrence - how often/year

#### Example

A piece of equipment is worth $10k, it partially fails twice per year. The cost to repair
is $2000. What is the ALE?

* ALE = SLE x ARO = 2 x 2000 = $4000

#### Example 2

A brand new HP server is worth $10k. it partially fails 20 time per 10 years. To cost to repair is
1/5 of the normal value. What is ALE?

* ALE = SLE x ARO = 2 x $2000 = $4000

### Measurement

* impact and likelihood of an occurrence can be measured as both:
  * quantitative: 1 -> 10, 1 -> 100
  * qualitative: low, medium, high, etc

### Risk register

* Identifies process
  * possible exposure
  * posible risks
  * likelihood of risks
  * gives overall risk

* impact x probability

#### example

During an inspection and auidotor requests to see a copy of indeitifed mission critical apps as 
well as their disaster recover plans. THe company being audited has an *SLA* around the app it
hosts. With which of the following is the auditor MOST likely concerned?

A. ARO/ALE
B. MTTR/MTBF
C. RTO/RPO
D. Risk assessment

* SLA is the key word here. Answer is C.

## BCP versus DRP

* Business continuity plan
  * how to sustain mission/business processes during/after a disruption
* Disaster recovery plan
  * for recovering info systems at an _alternate facility_

## Risk acceptance techniques

* accept - accept consequences
* transfer - insurance, have another org carry out function
* avoid - get out of the market
* mitigate - have a plan in case event happens to minimize impact

## disaster recovery

* hot site - almost ready to go - may lack data backup or some equip to be at 100% speed
* warm site - has basic functions, minimal equipment
* cold site - power and perhaps some very basic telecoms

## backup concepts

* full - entire backup
* incremental - everything since a previous backup
* differential - everything since last FULL backup. includes all incrementals & all differentials
                 in one file
* snapshots - a config frozen in time. doesnt include data, easily implemented in VMs
            - good before any major systems change or app upgrade

#### Example

* Backups at 18:00
| Day | Action |
| Mon | diff   |
| Tues | inc |
| Wed | diff |
| thurs | inc |
| fri  | FULL |
| sat  | inc |
| sun  | inc |

How many tapes will you need if there is a failure at 7 PM? 

* Need our FULL (fri), diff (Mon, includes sat & sun) & inc on tuesday. _3 tapes_

## Geographic and continued operations

### Geographic considerations

* off-site backups, distance, location selection
* legal implications - cloud
* data sovereignty - who does the data belong to

### Continuty of operation planning

* _exercises/tabletop_ - best way to set up a plan to continue ops?? this
* action-action reports - documentation, what we learned
* failover
* alternate processing sites
* alternate business practice

## Damage and loss control/incident response plan

 in order of importance...

* _preparation/incident response plan_
  * produce policies, establish contract, build kit, create checklist, build your team, practice, practice
* ID
  * validate before you cry WOLF
* Containment - turn off network, prevent spread
* Eradication - remove from network, isolate, fix, etc
  * find the root cause of the incident
* Recovery - turn network back on, continue services
* Lesson learned, documentation

## Forensics

* order of volatility - caches > RAM > SWAPFILES > DRIVES
* chain of custody - Document who has secure custody at all times
* data acquisition - immediately if possible
* capture system image - use a privkey of any data images created
* take hashes (DIGESTS) 
* Network traffic and logs
* record time offset - Document if servers are not using NTP
* screenshots
* witness interviews - immediately if possible

### First responder

* 1st responder is important
* first person notified and to react
* could be net or sys admin
* could be whoever is assigned to handle security incidents
* attempt to find root causes
* has to be prepared and his actions should be planned
  * hurried actions could damage potential evidence
  * usually has a toolkit
  * must follow an incident response plan

### Chain of custody

* always think of how it might be presented in court
* ID, collection, preservation, present in court
* evidence must be documented properly

## Compare and contrast various types of controls

* deterrent - sign or camera
* preventive - physical lock, passcode
* detective - camera, tapesystem, _logs_

* corrective - something you can reverse, getting data from backup
* compensating - not ideal, workaround solutions??? 

* technical - ACL on router/switch, username/password (IMS-ID mgmt sys, ACS- access control sys), smart card
* administrative - management - on paper, terms of use, signature, 
* physical - lock, gate, wall, preventative


