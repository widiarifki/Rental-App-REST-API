package com.widiarifki.rental.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.widiarifki.rental.model.Product;
import com.widiarifki.rental.connection.DBConnectionHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ProductServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		ObjectMapper mapper = new ObjectMapper();

        String sql = "SELECT name FROM products";
        Connection con = DBConnectionHandler.getConnection();
        ArrayList<Product> katProdukData = new ArrayList<Product>();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Product katProduk = new Product();
				katProduk.setName(rs.getString("name"));
				katProdukData.add(katProduk);
			}
        } catch (Exception e) {
            e.printStackTrace();
        }

		String output = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(katProdukData);
		res.setContentType("application/json");
		res.getWriter().println(output);
		res.getWriter().flush();
	}
}