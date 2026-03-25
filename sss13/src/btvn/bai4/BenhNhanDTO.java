package btvn.bai4;

import java.util.ArrayList;
import java.util.List;

public class BenhNhanDTO {
    int maBenhNhan;
    String tenBenhNhan;
    List<DichVu> dsDichVu = new ArrayList<>();

    public BenhNhanDTO(int maBenhNhan, String tenBenhNhan) {
        this.maBenhNhan = maBenhNhan;
        this.tenBenhNhan = tenBenhNhan;
    }
}
