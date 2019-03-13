## Bitcoin book

Book : mastering bitcoin it has internals of a block

Check out this book
https://github.com/bitcoinbook/bitcoinbook


http://adilmoujahid.com/posts/2018/03/intro-blockchain-bitcoin-python/
https://github.com/adilmoujahid/blockchain-python-tutorial
https://github.com/julienr/ipynb_playground/blob/master/bitcoin/dumbcoin/dumbcoin.ipynb
https://hackernoon.com/learn-blockchains-by-building-one-117428612f46

---


- Bitcoin is a deflationary currency. Total number of bitcoins = 21 million coins  by the year 2140.

- In fact, many modern wallets automatically create a new address for every transaction to maximize privacy. A wallet is simply a collection of addresses and the keys that unlock the funds within.

- One of the advantages of bitcoin over other payment systems is that, when used correctly, it affords users much more privacy. Acquiring, holding, and spending bitcoin does not require you to divulge sensitive and personally identifiable information to third parties. However, where bitcoin touches traditional systems, such as currency exchanges, national and international regulations often apply. In order to exchange bitcoin for your national currency, you will often be required to provide proof of identity and banking information. Users should be aware that once a bitcoin address is attached to an identity, all associated bitcoin transactions are also easy to identify and track. This is one reason many users choose to maintain dedicated exchange accounts unlinked to their wallets.


- When you do a transaction it gets sent to the network. The new block is added with the list of transactions every 10 mins to the blockchain.

- Use bitcoin explorer like [this](https://live.blockcypher.com/btc/) to see the live transactions happening.

- Unspent bitcoins [example](https://blockchain.info/unspent?active=1Cdid9KFAaatwczBwBttQcwXYCpvK8h7FK)

- you can run python scripts to query bitcoin

- Ownership of bitcoin is established through digital keys, bitcoin addresses, and digital signatures. The digital keys are not actually stored in the network, but are instead created and stored by users in a file, or simple database, called a wallet.

- Bitcoin uses elliptic curve multiplication as the basis for its cryptography.



---


Keys

- Generate a 256 bit private key. (Using a CSPRNG)