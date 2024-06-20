<!DOCTYPE html>
<html>
    <head>
        <title>Mahasiswa</title>
    </head>
    <body>
        <h1>form mahasiswa</h1>
        <form action="/save" method="post">
            <fieldset>
                <p>
                    NIM<br />
                    <input type="text" name="nim" required>
                </p>
                <p>
                    Nama<br />
                    <input type="text" name="nama" required>
                </p>
                <p>
                    Usia<br />
                    <input type="number" name="usia" required>
                </p>
                <p>
                    <input type="submit" value="Simpan">
                    <a href="/">Batal</a>
                </p>
            </fieldset>
        </form>
    </body>
</html>