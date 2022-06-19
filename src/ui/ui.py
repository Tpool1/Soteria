import PySimpleGUI as sg
from ..chain import chain
from ..load_patient import load_patient

bc = chain()

sg.theme("DarkAmber")

layout = [[sg.Text("Enter a Patient Image Directory:"), sg.InputText()],
            [sg.Text("Enter a Patient Clinical Path:"), sg.InputText()],
            [sg.Button("OK"), sg.Button("Cancel")]]

window = sg.Window("Soteria", layout)

while True:
    event, values = window.read()

    if event == sg.WIN_CLOSED or event == "Cancel":
        break

    elif event == "OK":

        p = load_patient(values[1], values[0])
 
window.close()
