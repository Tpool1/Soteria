import tkinter as tk
from tkinter import ttk

from chain import chain
from load_patient import load_patient
from batch_load import batch_load

class ui(tk.Tk):
    def __init__(self):
        super().__init__()

        self.bc = chain()

        self.title('Soteria')
        self.geometry('350x100')

        tabControl = ttk.Notebook(self)

        import_tab = ttk.Frame(tabControl)
        view_tab = ttk.Frame(tabControl)

        tabControl.add(import_tab, text='Import')
        tabControl.add(view_tab, text='View')

        tabControl.pack(expand=1, fill='both')

        self.single_button = ttk.Button(import_tab, text="Single Patient", command=self.singlePatient)
        self.batch_button = ttk.Button(import_tab, text="Batch", command=self.batch)

        self.img_label = tk.Label(import_tab, text="Enter a Patient Image Directory:")
        self.clinical_label = tk.Label(import_tab, text="Enter a Patient Clinical Path:")

        self.img_entry_var = tk.StringVar()
        self.clinical_entry_var = tk.StringVar()
        self.img_entry = ttk.Entry(import_tab, textvariable=self.img_entry_var)
        self.clinical_entry = ttk.Entry(import_tab, textvariable=self.clinical_entry_var)

        self.blockchain_info = tk.StringVar()
        self.blockchain_info.set(self.bc.getDescription())
        self.blockchain_info_label = ttk.Label(view_tab, textvariable=self.blockchain_info)

        self.img_label.grid(row=0, column=0)
        self.clinical_label.grid(row=1, column=0)

        self.img_entry.grid(row=0, column=1)
        self.clinical_entry.grid(row=1, column=1)

        self.single_button.grid(row=2, column=0)
        self.batch_button.grid(row=2, column=1)

        self.blockchain_info_label.grid(row=0, column=0)

    def singlePatient(self):
        p = load_patient(self.clinical_entry_var.get(), self.img_entry_var.get())

        # make genesis if adding first block
        if len(self.bc.chain) == 0:
            self.bc.makeGenesis(p)
        else:
            self.bc.makeBlock("patient added via UI", p)

        self.refreshView()

    def batch(self):
        patients = batch_load(self.clinical_entry_var.get(), self.img_entry_var.get())

        for p in patients:
            if len(self.bc.chain) == 0:
                self.bc.makeGenesis(p)
            else:
                self.bc.makeBlock("patient added via UI in a batch", p)

        self.refreshView()

    def refreshView(self):
        self.blockchain_info.set(self.bc.getDescription())
                