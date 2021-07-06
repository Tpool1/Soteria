package patientDataUtils;

public class dicom_data extends image{
    public int patient_id;
    public image patient_image;

    public void main(int patient_id, image patient_image) {
        this.patient_id = patient_id;
        this.patient_image = patient_image;
    }
}
