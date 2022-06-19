import PySimpleGUI as sg
from chain import chain
from load_patient import load_patient
from batch_load import batch_load

def ui():

    bc = chain()

    sg.theme("DarkAmber")

    layout = [[sg.Text("Enter a Patient Image Directory:"), sg.InputText()],
                [sg.Text("Enter a Patient Clinical Path:"), sg.InputText()],
                [sg.Button("Single Patient"), sg.Button("Batch"), sg.Button("Cancel")]]

    window = sg.Window("Soteria", layout)

    while True:
        event, values = window.read()

        if event == sg.WIN_CLOSED or event == "Cancel":
            bc.printChain()
            break

        elif event == "Single Patient":

            p = load_patient(values[1], values[0])

            # make genesis if adding first block
            if len(bc.chain) == 0:
                bc.makeGenesis(p)
            else:
                bc.makeBlock("patient added via UI", p)

        elif event == "Batch":

            patients = batch_load(values[1], values[0])

            for p in patients:
                if len(bc.chain) == 0:
                    bc.makeGenesis(p)
                else:
                    bc.makeBlock("patient added via UI in a batch", p)

    window.close()
