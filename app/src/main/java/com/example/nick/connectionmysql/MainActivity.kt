package com.example.nick.connectionmysql

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_hello.setOnClickListener {
            Thread(Runnable {
                val sql = "SELECT * FROM pos_shop "
                mysqlConnection(sql)
            }).start()
        }

    }
    /**
     * 连接数据库
     */
    fun mysqlConnection(sql:String) {
        var cn: Connection
        try {
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver")
            //建立连接
            cn = DriverManager.getConnection("jdbc:mysql://192.168.8.254:3306/ggboy_2",
                    "ggboy", "123456")
            val ps = cn.createStatement()
            val resultSet = ps!!.executeQuery(sql)
            while (resultSet.next()) {
                Log.d("mysqlConnection: " , resultSet.getString("sh_co_barcode"))
            }

            if (ps != null) {
                ps!!.close()
            }
            if (cn != null) {
                cn.close()
            }
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        } catch (e: SQLException) {
            e.printStackTrace()
        }

    }
}
