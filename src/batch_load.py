import os
from load_patient import load_patient

def batch_load(clinical_path, image_dir):
    patients = []
    for p_dir in os.listdir(image_dir):
        p = load_patient(clinical_path, os.path.join(image_dir, p_dir))
        patients.append(p)

    return patients
