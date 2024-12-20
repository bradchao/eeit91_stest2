package tw.brad.stest2;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import tw.brad.stest2.model.Hotel;

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
	
	
	
}
