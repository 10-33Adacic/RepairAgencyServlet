package ua.training.model.dao.mapper;

import ua.training.model.entity.Request;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RequestMapper {
    public Request extractFromResultSet(ResultSet rs)
            throws SQLException {
        return Request.builder()
                .id(rs.getLong("id"))
                .request(rs.getString("request"))
                .status(rs.getString("status"))
                .price(rs.getLong("price"))
                .reason(rs.getString("reason"))
                .creator(rs.getString("creator"))
//                .requestNumber(rs.getLong("request_number"))
                .build();
    }
}
