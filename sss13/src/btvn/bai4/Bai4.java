package btvn.bai4;

import btth.utils.DataConnect;

import java.sql.*;
import java.util.*;

public class Bai4 {

    public static List<BenhNhanDTO> getDanhSach() {

        List<BenhNhanDTO> result = new ArrayList<>();
        Map<Integer, BenhNhanDTO> map = new HashMap<>();

        try (Connection conn = DataConnect.openConnection()) {

            String sql = "select bn.maBenhNhan, bn.tenBenhNhan, dv.maDichVu, dv.tenDichVu " +
                    "from benhnhan bn left join dichvusudung dv " +
                    "on bn.maBenhNhan = dv.maBenhNhan";

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int maBN = rs.getInt("maBenhNhan");
                String tenBN = rs.getString("tenBenhNhan");

                BenhNhanDTO bn = map.get(maBN);

                if (bn == null) {
                    bn = new BenhNhanDTO(maBN, tenBN);
                    map.put(maBN, bn);
                }

                int maDV = rs.getInt("maDichVu");
                String tenDV = rs.getString("tenDichVu");

                if (!rs.wasNull()) {
                    bn.dsDichVu.add(new DichVu(maDV, tenDV));
                }
            }

            result.addAll(map.values());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
