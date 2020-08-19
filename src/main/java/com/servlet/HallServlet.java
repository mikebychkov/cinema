package com.servlet;

import com.model.Seat;
import com.store.Storage;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HallServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Seat> data = Storage.getInstance().getHall();

        Map<Integer, List<Seat>> map = new HashMap<>();

        int row = 1;
        int col = 1;
        List<Seat> rowData = null;
        for (Seat s : data) {
            if (col == 1) {
                rowData = new ArrayList<>();
            }
            rowData.add(s);
            if (col == 3) {
                map.put(row++, rowData);
                col = 1;
                continue;
            }
            col++;
        }

        JSONObject jo = new JSONObject(map);

        resp.setContentType("text/json");

        PrintWriter writer = resp.getWriter();
        writer.println(jo.toString());
        writer.flush();
    }
}
