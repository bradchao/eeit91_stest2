package tw.brad.stest2;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import tw.brad.stest2.model.Hotel;
import tw.brad.stest2.model.Member;
import tw.brad.utils.BCrypt;

@RestController
@RequestMapping("/brad03")
public class Brad03 {

	@RequestMapping("/test1")
	public void test1() {
		String url = "https://data.moa.gov.tw/Service/OpenData/ODwsv/ODwsvTravelStay.aspx";
		
		RestTemplate template = new RestTemplate();
		String content = template.getForObject(url, String.class);
		
		System.out.println(content);
	}
	
	@RequestMapping("/test2")
	public void test2() {
		String hotel = "[{\"name\":\"aaa\", \"addr\":\"bbb\", \"tel\":\"ccc\"},{\"name\":\"aaa\", \"addr\":\"bbb\", \"tel\":\"ccc\"}]";

		ObjectMapper mapper = new ObjectMapper();
		try {
			List<Hotel> list = mapper.readValue(hotel, new TypeReference<List<Hotel>>() {});
			for (Hotel hh: list) {
				System.out.printf("%s:%s:%s\n", hh.getName(), hh.getAddr(), hh.getTel());
			}

			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;	
	
	@RequestMapping("/test3")
	public void test3() {
		String url = "https://data.moa.gov.tw/Service/OpenData/ODwsv/ODwsvTravelStay.aspx";
		RestTemplate template = new RestTemplate();
		String content = template.getForObject(url, String.class);
		
		List<Hotel> list = new LinkedList<Hotel>();
		JSONArray root = new JSONArray(content);
		for (int i=0; i<root.length(); i++) {
			JSONObject row = root.getJSONObject(i);
			Hotel hotel = new Hotel(row.getString("Name"), row.getString("Address"), row.getString("Tel"));
			list.add(hotel);
		}
		
		String sql = "INSERT INTO hotel (name,addr,tel) VALUES (:name, :addr, :tel)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		MapSqlParameterSource[] datas = new MapSqlParameterSource[list.size()];
		for (int i = 0; i<list.size(); i++) {
			Hotel hotel = list.get(i);
			datas[i] = new MapSqlParameterSource();
			datas[i].addValue("name", hotel.getName());
			datas[i].addValue("addr", hotel.getAddr());
			datas[i].addValue("tel", hotel.getTel());
		}
		namedParameterJdbcTemplate.batchUpdate(sql, datas, keyHolder);
		
	}
	
	
	
}
