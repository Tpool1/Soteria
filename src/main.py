from src.block import block

class chain():
    def __init__(self):
        self.unconfirmed_transactions = []
        self.chain = []
        self.difficulty = 5

    def makeGenesis(self, patient):
        b = block(index=0, previousHash=None, label="genesis", patient=patient)
        b.mineBlock(self.difficulty)
        self.chain.append(b)
        return b
