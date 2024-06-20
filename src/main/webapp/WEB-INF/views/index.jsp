<%@ page import="java.util.List" %>
<%@ page import="com.example.projek.models.Mahasiswa" %>
<%
    List<Mahasiswa> d= (List<Mahasiswa>) request.getAttribute("data");
%>
<!DOCTYPE HTML>
<html>
    <head>
        <title>Mahasiswa</title>
    </head>
    <body>
        <h1>Mahasiswa</h1>
        <p>
            Status Connection : <b>${connStatus == "Connected" ? "<font color='green'>Connected</font>" : "<font color='red'>Disconnected</font>"}</b> <font color='red'>${connMessage}</font>
        </p>
        <p>
            Jumlah Data : <%= d.size() %>
        </p>
        <p>
            <a href="/add">Tambah</a>
        </p>
        <table width="100%" border="1" rules="all" cellpadding="5">
            <thead>
                <tr bgcolor="grey" style="color: white;">
                    <th>No.</th>
                    <th>NIM</th>
                    <th>Nama</th>
                    <th>Usia</th>
                    <th>Aksi</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (int i=0; i< d.size(); i++) { %>
                        <tr>
                            <td><%= i + 1 %></td>
                            <td><%= d.get(i).getNim() %></td>
                            <td><%= d.get(i).getNama() %></td>
                            <td><%= d.get(i).getUsia() %></td>
                            <td>
                                <a href="/edit?nim=<%= d.get(i).getNim() %>">Ubah</a> | <a href="/delete?nim=<%= d.get(i).getNim() %>" onclick="return confirm('Apakah anda yakin akan menghapus data?')">Hapus</a>
                            </td>
                        </tr>
                    <% } %>
                <% if (d.size() == 0) { %>
                    <tr>
                        <td colspan="5" align="center">Tidak ada data</td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    </body>
</html>