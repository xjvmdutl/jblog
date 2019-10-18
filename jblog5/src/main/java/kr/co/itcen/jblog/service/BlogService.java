package kr.co.itcen.jblog.service;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import kr.co.itcen.jblog.exception.FileUploadException;
import kr.co.itcen.jblog.repository.BlogDao;
import kr.co.itcen.jblog.repository.CategoryDao;
import kr.co.itcen.jblog.repository.PostDao;
import kr.co.itcen.jblog.vo.BlogVo;
import kr.co.itcen.jblog.vo.CategoryVo;
import kr.co.itcen.jblog.vo.PostVo;

@Service
public class BlogService {
	@Autowired
	private BlogDao blogDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private PostDao postDao;
	private static final String SAVE_PATH="/uploads";
	private static final String URL_PREFIX="/images";
	public List<CategoryVo> getList(String id) {	
		return categoryDao.getList(id);
	}
	public List<PostVo> getList(Long categoryno) {	
		return postDao.getList(categoryno);
	}
	public PostVo getPost(Long postno,Long categoryno) {
		return postDao.getPost(postno,categoryno);
	}	
	public BlogVo get(String id) {
		return blogDao.get(id);
	}
	public Boolean update(MultipartFile multipartFile, BlogVo vo) {
		String url ="";
		if (multipartFile == null)
			return false;
		String originalFilename = multipartFile.getOriginalFilename();
		String extName = originalFilename.substring(originalFilename.lastIndexOf('.') + 1);
		String saveFileName = generateSaveFilename(extName);
		try {
			byte[] fileDate = multipartFile.getBytes();
			OutputStream os=new FileOutputStream(SAVE_PATH+"/"+saveFileName);
			os.write(fileDate);
			os.close();
			url=URL_PREFIX+"/"+saveFileName;
		} catch (IOException e) {
			throw new FileUploadException();
		}
		vo.setLogo(url);
		return blogDao.update(vo);
	}
	private String generateSaveFilename(String extName) {
		String filename = "";
		Calendar calendar = Calendar.getInstance();
		filename += calendar.get(Calendar.YEAR);
		filename += calendar.get(Calendar.MONTH);
		filename += calendar.get(Calendar.DATE);
		filename += calendar.get(Calendar.HOUR);
		filename += calendar.get(Calendar.MINUTE);
		filename += calendar.get(Calendar.SECOND);
		filename += calendar.get(Calendar.MILLISECOND);
		filename += (".") + extName;
		return filename;
	}
	public List<CategoryVo> getCategoryList(String id) {
		return categoryDao.getCategoryList(id);
	}
	public List<CategoryVo> getCategorytitle(String id) {
		List<CategoryVo> list=categoryDao.getCategorytitle(id);
		return list;
	}
	public Boolean insertPost(PostVo vo) {
		return postDao.insert(vo);
	}
	
}