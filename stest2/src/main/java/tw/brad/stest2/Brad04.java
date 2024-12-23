package tw.brad.stest2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.brad.stest2.mapper.HotelRowMapper;
import tw.brad.stest2.model.Hotel;

@RestController
@RequestMapping("/brad04")
public class Brad04 {
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	private HotelRowMapper hotelRowMapper;
	
	@GetMapping("/hotels")
	public List<Hotel> test1() {
		String sql = "SELECT id, name, addr, tel FROM hotel";
		
		Map<String, Object> params = new HashMap<>();
		List<Hotel> list = namedParameterJdbcTemplate.query(sql, params, hotelRowMapper);
	
		return list;
	}
	
	@GetMapping("/hotel/{id}")
	public Hotel test2(@PathVariable int id) {
		String sql = "SELECT id, name, addr, tel FROM hotel WHERE id = :id";
		
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		
		List<Hotel> list = namedParameterJdbcTemplate.query(sql, params, hotelRowMapper);
		//System.out.println(list.size());
		
		if (list.size() > 0) {
			return list.get(0);
		}else {
			Hotel hotel = new Hotel();
			hotel.setErrorCode(1);
			return hotel;
		}
	}
	
	

}
