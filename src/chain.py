import numpy as np
from block import block
from patient import patient

class chain:
    def __init__(self):
        self.unconfirmed_transactions = []
        self.chain = []
        self.difficulty = 5

    def makeGenesis(self, patient):
        b = block(index=0, previousHash=None, label="genesis", patient=patient)
        b.mineBlock(self.difficulty)
        self.chain.append(b)
        return b

    def makeBlock(self, label, patient):
        index = len(self.chain)
        previousHash = self.chain[index-1].currentHash
        b = block(index, previousHash, label, patient)
        b.mineBlock(self.difficulty)
        self.chain.append(b)

    def printChain(self):
        for block in self.chain:
            print("- - - - - - - - - -")
            print("Index:", block.index)
            print("Previous Hash:", block.previousHash)
            print("Label:", block.label)
            print("Time Stamp:", block.timestamp)
            print("Current Hash:", block.currentHash) 
            print("- - - - - - - - - -")