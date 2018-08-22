# Vertcoin-Wallet
This is a second-year assignment for an Algorithms & Data Structures module. This program generates a private key for a Vertcoin Paper Wallet. The code for SHA256 was provided to us. We were required to write the code to generate the private key. To do this, I did the following:

- I generated a random 64-digit hexadecimal string (which I named "pk") and added 80 to the end of this string (named "pk80").
- I then computed the SHA256 of this, calling it "hash". 
- And again computed the SHA256 of the "hash", deeming this "double hash". 
- I then added the first 8 digits of the "double hash" onto the end of the "pk80" string, calling it "concat".
- I computed the Base58 encoding of the "concat" string and ended up with my result.

We were then advised to use a website (https://walletgenerator.net/?currency=Vertcoin) to generate a public key from our result.
