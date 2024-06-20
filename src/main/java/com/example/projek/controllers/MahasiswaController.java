package com.example.projek.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.projek.models.Mahasiswa;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MahasiswaController {
    private String url = "jdbc:postgresql://localhost:5432/simak";
    private String user = "postgres";
    private String password = "Ramzy0305";

    private String connStatus = "Disconnected";
    private String connMessage = "";

    private Connection conn;
    private Statement stmnt;
    private String sql;

    @RequestMapping("/")
    public String index(Model model) {
        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(url, user, password);

            connStatus = "Connected";

            stmnt = conn.createStatement();

            sql ="SELECT * FROM public.mahasiswa";

            ResultSet rs = stmnt.executeQuery(sql);

            List<Mahasiswa> data = new LinkedList<>();

            while (rs.next()) {
                Mahasiswa mhs = new Mahasiswa();

                mhs.setNim(rs.getString("nim"));
                mhs.setNama(rs.getString("nama"));
                mhs.setUsia(rs.getInt("usia"));

                data.add(mhs);
            }

            model.addAttribute("data", data);
        } catch (Exception e) {
            // TODO: handle exception
            connMessage = e.getMessage();
        }

        model.addAttribute("connStatus", connStatus);
        model.addAttribute("connMessage", connMessage);

        return "index";
    }

    @RequestMapping("/add")
    public String add() {
        return "form";
    }

    @RequestMapping("/save")
    public String save(Model model, Mahasiswa mhs) {
        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(url, user, password);

            connStatus = "Connected";

            stmnt = conn.createStatement();

            sql ="INSERT INTO public.mahasiswa (nim, nama, usia) VALUES ('" + mhs.getNim() + "', '" + mhs.getNama() + "', '" + mhs.getUsia() + "')";

            stmnt.execute(sql);
        } catch (Exception e) {
            // TODO: handle exception
            connMessage = e.getMessage();
        }

        model.addAttribute("connStatus", connStatus);
        model.addAttribute("connMessage", connMessage);

        return "redirect:/";
    }
    
    @RequestMapping("/edit")
    public String edit(Model model, @RequestParam String nim) {
        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(url, user, password);

            connStatus = "Connected";

            stmnt = conn.createStatement();

            sql ="SELECT * FROM public.mahasiswa WHERE nim = '" + nim + "'";

            ResultSet rs = stmnt.executeQuery(sql);

            rs.next();

            Mahasiswa mhs = new Mahasiswa(rs.getString("nim"), rs.getString("nama"), rs.getInt("usia"));

            model.addAttribute("mhs", mhs);
        } catch (Exception e) {
            connMessage = e.getMessage();
        }

        model.addAttribute("connStatus", connStatus);
        model.addAttribute("connMessage", connMessage);

        return "detail";
    }

    @RequestMapping("/update")
    public String update(Model model, Mahasiswa mhs, @RequestParam String id) {
        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(url, user, password);

            connStatus = "Connected";

            stmnt = conn.createStatement();

            sql ="UPDATE public.mahasiswa SET nim = '" + mhs.getNim() + "', nama = '" + mhs.getNama() + "', usia = '" + mhs.getUsia() + "' WHERE nim = '" + id + "'";

            stmnt.execute(sql);
        } catch (Exception e) {
            connMessage = e.getMessage();
        }

        model.addAttribute("connStatus", connStatus);
        model.addAttribute("connMessage", connMessage);

        return "redirect:/";
    }

    @RequestMapping("/delete")
    public String delete(Model model,@RequestParam String nim) {
        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(url, user, password);

            connStatus = "Connected";

            stmnt = conn.createStatement();

            sql ="DELETE FROM public.mahasiswa WHERE nim = '" + nim + "'";

            stmnt.execute(sql);
        } catch (Exception e) {
            connMessage = e.getMessage();
        }

        model.addAttribute("connStatus", connStatus);
        model.addAttribute("connMessage", connMessage);

        return "redirect:/";
    }
}