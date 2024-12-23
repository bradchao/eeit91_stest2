package tw.brad.stest2;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tw.brad.stest2.model.MemberForm;

@RestController
@RequestMapping("/brad06")
public class Brad06 {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@PostMapping("/member/{id}")
	public void test1(@RequestParam MultipartFile upload, @PathVariable int id) {
	
		try {
			byte[] fileBytes = upload.getBytes();
			String sql = "UPDATE member SET icon = :icon WHERE id = :id";
			
			Map<String, Object> data = new HashMap<>();
			data.put("icon", fileBytes);
			data.put("id", id);
			
			int n = namedParameterJdbcTemplate.update(sql, data);
			System.out.println("OK");
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	@PostMapping("/member")
	public void test2(@ModelAttribute MemberForm memberForm) {
		
		String uploadDir = "C:\\Users\\User\\git\\repository5\\stest2\\src\\main\\resources\\upload\\";
		
		List<MultipartFile> files = memberForm.getFiles();
		for (MultipartFile file : files) {
			if (!file.isEmpty()) {
				String uploadFile = uploadDir + file.getOriginalFilename();
				try {
					file.transferTo(new File(uploadFile));
				}catch(Exception e) {
					System.out.println(e);
				}
			}
		}
		
		
	}
	
	
	
	
}
