import pydicom
import os
import pandas as pd

from patient import patient

def load_patient(clinical_path, img_dir):

    # load image data and patient id
    load_paths = list()
    for (dirpath, dirnames, filenames) in os.walk(img_dir):
        load_paths += [os.path.join(dirpath, file) for file in filenames]
    
    img_files = []
    ids = []
    for path in load_paths:
        file = pydicom.dcmread(path)
        img_files.append(file)
        id = file.PatientID
        ids.append(id)

    # raise error if not all images have the same id
    assert len(set(ids)) == 1
    p_id = ids[0]

    # extract only numbers from p_id
    p_id = [int(s) for s in p_id.split() if s.isdigit()]

    # load clinical data
    df = pd.read_csv(clinical_path)
    df = df.set_index(df.columns[0])
    
    p_clinical = df[p_id]

    p = patient(p_id, p_clinical, img_files)

    return p