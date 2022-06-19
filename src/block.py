import time
import json
from hashlib import sha256

class block:
    def __init__(self, index, previousHash, label, patient):
        self.index = index
        self.previousHash = previousHash
        self.label = label
        self.patient = patient
        self.nonce = 0

        self.timestamp = time.time()
        self.currentHash = self.calculateHash()

    def calculateHash(self):
        input = str(self.index) + str(self.timestamp) + str(self.previousHash) + str(self.label) + str(self.nonce)
        hash = sha256(input.encode()).hexdigest()
        return hash
    
    def mineBlock(self, difficulty):
        computed_hash = self.calculateHash()
        while not computed_hash.startswith('0' * difficulty):
            self.nonce += 1
            computed_hash = self.calculateHash()
        return computed_hash
        