package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.PowerOutage;

public class PowerOutageDAO {
	
	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}
	
	
	public List<PowerOutage> readOutages(Nerc nerc){
		List<PowerOutage> risultato= new ArrayList<>();
		String sql= "SELECT id,customers_affected,date_event_began,date_event_finished, "
				+ "((UNIX_TIMESTAMP(date_event_finished)-UNIX_TIMESTAMP(date_event_began)) / 3600) AS durata "
				+ "FROM poweroutages WHERE nerc_id=? ORDER BY date_event_began";
		
		try {
			Connection conn= ConnectDB.getConnection();
			PreparedStatement st= conn.prepareStatement(sql);
			st.setInt(1, nerc.getId());
			ResultSet rs= st.executeQuery();
			
			while(rs.next()) {	
				LocalDate inizio=rs.getDate("date_event_began").toLocalDate();
				LocalDate fine=rs.getDate("date_event_finished").toLocalDate();
						
				PowerOutage po= new PowerOutage(rs.getInt("id"),rs.getInt("customers_affected"),inizio,fine,rs.getDouble("durata"));
				risultato.add(po);
			}
			
			conn.close();
			return risultato;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	

}
