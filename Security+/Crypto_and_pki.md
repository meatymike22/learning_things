# Security + Notes

## Crypto & PKI Cryptography

  _Confidentiality_: Encryption - unauthorized access, in storage, in transit
  _Integrity_: hashes/digests/checksums - in storage, in transit, in use/process
  _Availability_: fault tolerance - backups, acceptable levels of access

* Steganography: hiding data in plain sight

### Symmetric Encryption

* all users have the same key to encrypt/decrypt
* Caesar code: ROT(ation) 
  * every letter rotated by key (13 letters)
  * SUBSTITUTION cipher
* poly alphabet subsititution cipher
  * modern
  * every letter is substituted using a different code by rotating an inner key mapped ring 
    instead of 1:1 mapping
* Digital Symmetric encryption
  * convert ASCII text to binary, swap 1st and 2nd group of 4 bits
  * E = 01000101 -> 01010100 = T

#### Protocols

* AES - 128 to 256 bits - very good
* 3DES - older but good
* DES- no secure
* RC4- live streaming, audio/video, unknown file sizes
* blowfish/twofish - obsolete

### PKI - Public Key Infrastructure

* trusted protocols for encryption & integrity
* trusted partners - Certificate authorities

*3 main protocols*

* _Hashing_: fixed length file that is a checksum of the original file
           sent along with the file to prove integrity. also called 
           fingerprint/checksum

* _diffie-hellman_: key exchange protocol. We can create secret keys between 2 parties
                  without any pre-secret & eavesdroppers cannot discover the secret

* _asymmetric crypto_: 2 different keys, private (only known to sender/recipient) & public

#### Hashing

__MOST COMMON HASHES (must know for exam)__

| protocol     | MD5 | SHA1 | SHA2 | SHA3    |
|--------------|-----|------|------|---------|
| size in bits | 128 | 160  | 256  | 256-512 |

* the digest string uses hex characters. Md5 is 32 hex bits long (hex is 4 bits long)
* One character difference can completely change a hash beccause of obfuscation of rows, 
  which entails moving a block of 8 bits into other rows for XOR addition

* hashing a secret/key is called HMAC - Hashed Message Authentication Code


#### Diffie Hellman

* Diffie hellman KEX is how 2 parties can exhange 




#### Asymmetric Encryption

* Intro:
  diffie hellman is used as an extra layer of security for the SSL/TLS process
  the server cert is what verifies you know who you are talking to

* this type of encryption uses 2 different keys 
* Asymmetrical keys work oppositionally to each other
* _*more computationally intensive than symmetric encryption*_
* you can only decrypt a public key with the associated private key
  Anyone on the internet should then encrypt their message with your public key,
  and then you decrypt with your private key. This process can be vice-versa, but the 
  public key encryption is used for private messaging
* Encrypting with a private key is used for _non-repudiation_, or verifying the 
  sender's identity

Two popular protocols: RSA & ECC (Elliptical curve cryptography)

Ciphers:
* block ciphers are used for known file sizes
* stream ciphers are used for real time/live streams. Processor intensive


|                    | AES   | 3DES  | RSA   | ECC  | DSA   | RC4    |
|--------------------|-------|-------|-------|------|-------|--------|
| type of encryption | sym   | sym   | asym  | asym | asym  | sym    |
| type of cipher     | block | block | block | blok | block | stream |


#### Certificate Authority

* all browsers trust CAs
* CA validates you are who you say you are
* CA signs your public key cert to say it trusts that it is your pubkey
* if a message signed, then you sent it
* if a trusted CA says it is google.com, then it is

* CA's are already built into your browser by the developer
* if one of the trusted CA's validates a website's identitties then you are good

* Certificate managment
  * Certificate sign request:
    * you generate public/private key pair and send the pubkey to the CA
    * they sign your pubkey with their privkey
    * anyone with a modern web browser can then validate that the CA signed it because
      the CA's pubkey can decrypt it

  * CRL - Cert revocation list
    * published by CA - Certs that have been compromised
    * List of all in date revoked certs - long
    * reference to url list is in the public cert

  * OCSP
    * Online cert status protocol
    * check for just single cert- faster
    * more ubiquitous than CRL

* SSL/TLS (port 443), HTTPS (443), SSH * are all very similar
  * Web server has a CA signed cert displaying its pubkey
  * client validates it knows who it's talking to using CA signed cert
  * to make the communication secure in both directions DHKE is used to 
    negotiate a symmetric key
  * both sides use AES to talk symmetrically
  * communication channel is now secure

* SSL/TLS sequence
  * PC connects to https server
  * cert confirms pubkey and identity of server (no MITM)
  * DHKE is started to determine best symmetric key for session
  * key negotiation complete & session is started
  * SSL/TLS is application layer encryption, IPSEC for VPNs is network layer

* Signing Documents/message (digital certificate)
  * document's hash is created
  * hash is encrypted w sneder's private key
  * decrpyted w sender's pubkey
  * hash is then computed and compared
  * this is _non-repudiation_ because we know the sender sent it

* PKI relies on Asymmetric crypto aka public/private key pairs
* RSA or ECC is for signing certs + emails
  * we sign a hashed digest with a priv asym key to create a cert
* DH for generating/exchanging the symmetric keys with the now 
  trusted other side
  * can use PFS (perfect forwarding squence) w DH

* Hashing functions used for integrity
* hashes that add a key are called HMACs
* HMACs are used for non-repudiation only if you use your private key. digital sig.
  only YOU could have encrypted it if your public key can decrypt it
* you can add salt, initialization vector, or nonce value to add to the hash that helps the integrity

*Key strength*:
* VIP - every extra bit doubles the possibilities for a brute force attack
* ranges rom 128-256 for symmetric algs
* ranges from 1024 - 4096 for aysm algs RSA
* 256 for asym ECC algs
* longer keys = more processing power for asym
* ECC is more faster is considered secure, but is a recent development and hasnt been fully tested yet
* ECC is popular for mobile devices
* ECC _p-384_ curve is the most secure 
* must use asymmetric for certificates
* symmetric is used for session/transfer of data

Ciphers:
* stream: calcualted 1 byte at a time used for streaming
* block: calculated on 64-128 bit blocks

Keys:
* session: symm key created using DH that secures channel for the sesh
* ephemeral: symm key only alid for a short time & keeps changing during sesh
* PFS: perfect forward secrecy: uses eph keys; a very secure implementation of DH 
       during DHKE stage
* rot13 - caesar cipher rotates by 13
* DSA - digital sig alg - older process simlar to RSA. no longer popular

Attacks:
* Dictionary attack
  * humans are very predictable
  * Password, password1, password1!
  * if there is a means of continuously trying passwords, this may work
  * if 3 tries then locked out, this method wont work
* Rainbow table
  * for attacking a hashed password from a stolen database table
  * salting a hash prevents rainbow attacks

* Salt: data added to hash to prevent rainbow attacks
* IV/None/Challenge: data added during negotiation of wireless networking keys 
                     to make them harder to brute force/crack
* Key stretching: expands keys to use different parts over different iterations of the encrypting/hashing
  * 2 popular methods: PBKDF2 and bcrypt

### Data State modes:

* Data at rest: storage
  * hard drives, tape systems, database data

* data in transit: over the wire/air
  * encryption of email S/MIME
  * VPNs, PTP, L2TP, PTPP, IKE, IPSec, 1.3TLS for websites (https)

* data in use
  * in order of volatility swap files, RAM memory ,cache(most volatile)
  * volatility is important when considering an incident response/investigation

* Stapling
  * can give a time duration validity so checks are not required for subsequent connections 
    during that interval

* Pinning
  * Allows you to whitelist your own pubkey, which will stay valid even if an upstream cert is revoked
  * often used in software/apps so the app trusts the Applications web server implicitly

### more concepts

* Captive portal - accept our COU, enter email
* Key escrow - you trust someone to keep your private key
* wildcard certs - *.google.com: drive.google.com and photo.google.com attached to one cert
* SAN certs - Names multiple different domains in one cert; costs more than a single domain SSL cert

### Cert formats

* DER - Binary (remember digits)
* CER - ASCII (remember chars)
* PEM - DER or CER - *most common*
* PFC (win)
* P12 (linux/OSx)
* P7b - public certs and associated chain certs

### Some notes

* if you want 3-way comms, have to use symmetric encryption
* asymmetric encryption is only 2-way









