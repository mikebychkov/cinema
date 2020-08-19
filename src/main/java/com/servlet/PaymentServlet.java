package com.servlet;

import com.model.Seat;
import com.store.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PaymentServlet extends HttpServlet {

    private Seat getSeat(int id) {
        Seat rsl = Storage.getInstance().getSeat(id);
        if (rsl == null) {
            rsl = new Seat();
        }
        return rsl;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        req.setAttribute("seat", getSeat(Integer.parseInt(idParam)));
        req.getRequestDispatcher("payment.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("username");
        String phone = req.getParameter("phone");
        String id = req.getParameter("id");

        Storage.getInstance().registerAcc(name, phone, Integer.parseInt(id));

        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }
}
