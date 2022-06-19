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

    def getDescription(self):
        des = ""
        for block in self.chain:
            des+="- - - - - - - - - -\n"
            des+="Index: "
            des+=str(block.index)+"\n"
            des+="Previous Hash: "
            des+=str(block.previousHash)+"\n"
            des+="Label: "
            des+=str(block.label)+"\n"
            des+="Time Stamp: "
            des+=str(block.timestamp)+"\n"
            des+="Current Hash: "
            des+=str(block.currentHash)+"\n"
            des+="- - - - - - - - - -"

        return des

    def printChain(self):
        des = self.getDescription()
        print(des)
