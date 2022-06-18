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
        block_string = json.dumps(self.__dict__, sort_keys=True)
        return sha256(block_string.encode()).hexdigest()
    
    def mineBlock(self, difficulty):
        computed_hash = self.calculateHash
        while not computed_hash.startswith('0' * difficulty):
            self.nonce += 1
            computed_hash = self.calculateHash()
        return computed_hash
        