package com.tibame.tga105.forum.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tibame.tga105.forum.Service.ArticleService;
import com.tibame.tga105.forum.Service.ArticleTypeService;
import com.tibame.tga105.forum.Service.CollectionService;
import com.tibame.tga105.forum.Service.EmailServices;
import com.tibame.tga105.forum.Service.ReplyService;
import com.tibame.tga105.forum.Service.ReportService;
import com.tibame.tga105.forum.Service.ReportTypeService;
import com.tibame.tga105.forum.entity.ArticleEntity;
import com.tibame.tga105.forum.entity.ArticleTypeEntity;
import com.tibame.tga105.forum.entity.CollectionEntity;
import com.tibame.tga105.forum.entity.ReplyEntity;
import com.tibame.tga105.forum.entity.ReportEntity;
import com.tibame.tga105.forum.entity.ReportTypeEntity;
import com.tibame.tga105.user.entity.UserVO;
import com.tibame.tga105.user.security.UserPrincipal;
import com.tibame.tga105.user.service.UserService;

@Controller
public class ForumController {
	@Autowired
	ArticleService articleService;

	@Autowired
	ArticleTypeService articleTypeService;

	@Autowired
	ReplyService replyService;

	@Autowired
	UserService userService;

	@Autowired
	CollectionService collectionService;
	
	@Autowired
	ReportService reportService;
	
	@Autowired
	ReportTypeService reportTypeService;
	
	@Autowired
	EmailServices emailServices;
	
	
	
	@GetMapping("/read")
	public String read(Model model) {

		return "forumRead";
	}

	@GetMapping("/read/{id}")
	public String Read(@PathVariable Integer id, 
						@RequestParam(name="value",required = false) Integer value,
						Model model) {
		ArticleEntity articleEntity = articleService.findById(id);
		if(value!= null) {
		ReportEntity report=reportService.findOne(value);
		model.addAttribute("report", report);
		}
		
		if(articleEntity !=null) {
			articleEntity.setViewcount(articleEntity.getViewcount()+1);
			articleService.add(articleEntity);
		}
		
		UserVO uservo = null;
		UserPrincipal principal1 = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		uservo = principal1.getUservo();
		ReplyEntity reply = new ReplyEntity();
		if (articleEntity == null) {
			articleEntity = new ArticleEntity();
		}

		List<ReplyEntity> list = replyService.find(articleEntity.getArticleid());
		reply.setArticleEntity(articleEntity);
		UserVO user = reply.getUserVo();

		
		List<CollectionEntity> coll=collectionService.find(uservo.getUserid());
		System.out.println("-------------"+coll);
		

		model.addAttribute("articleEntity", articleEntity);
		model.addAttribute("lister", list);
        model.addAttribute("user",uservo);
        model.addAttribute("coll",coll);
        
        
		return "forumRead";
	}
	
		@GetMapping("/readGuest/{id}")
		public String ReadGuest(@PathVariable Integer id, 
											  Model model) {
			
		ArticleEntity articleEntity = articleService.findById(id);
		
		
		ReplyEntity reply = new ReplyEntity();
		
		if (articleEntity == null) {
			articleEntity = new ArticleEntity();
		}

		List<ReplyEntity> list = replyService.find(articleEntity.getArticleid());
		reply.setArticleEntity(articleEntity);
		UserVO user = reply.getUserVo();

		model.addAttribute("articleEntity", articleEntity);
		model.addAttribute("lister", list);

		return "forumGuestRead";
	}

	@RequestMapping("/forumPost")
	public String create(Model model) {
		model.addAttribute("articleEntity", new ArticleEntity());
		List<ArticleTypeEntity> articleEntityList = articleTypeService.findAll();
		model.addAttribute("articleall", articleEntityList);
		return "forumPost";
	}

	/*
	 * 加入Valid驗證 錯誤訊息交給Spring容器處理，因為與其他參數擺放執行順序有問題，暫時先拿掉
	 */
	@PostMapping("/create")
	public String create(final RedirectAttributes redirectAttributes, 
						Model model,
						@RequestParam String articletitle,
						@RequestParam String articlecontext, 
						@RequestParam(name = "articletypeid") Integer typeid, 
						@RequestParam(name="file")MultipartFile file						
						) throws IOException {
		
		
		if (articletitle==null || articletitle.trim().length()==0) {
			redirectAttributes.addFlashAttribute("errmsgs", "以下欄位不能為空");
			
			redirectAttributes.addFlashAttribute("title", "文章標題不能為空");
			
			
			
			
		}
		if (articlecontext ==null || articlecontext.trim().length()==0) {
			redirectAttributes.addFlashAttribute("errmsgs", "以下欄位不能為空");
			
			redirectAttributes.addFlashAttribute("context", "文章內容不能為空");
			
			
		}
		if(articletitle.equals("") || articlecontext.equals("")) {
			List<ArticleTypeEntity> articleEntityList = articleTypeService.findAll();
			redirectAttributes.addFlashAttribute("articletitle", articletitle);
			redirectAttributes.addFlashAttribute("articlecontext", articlecontext);
			redirectAttributes.addFlashAttribute("articletypeid", typeid);
			model.addAttribute("articleall", articleEntityList);
			
			
			return "redirect:/forumPost";
			}
		
		ArticleEntity articleEntity = new ArticleEntity();
		UserVO uservo = null;
		UserPrincipal principal1 = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		uservo = principal1.getUservo();
		articleEntity.setArticlecontext(articlecontext);
		articleEntity.setArticletitle(articletitle);
		ArticleTypeEntity articleTypeEntity = articleTypeService.find(typeid);
		articleEntity.setArticleTypeEntity(articleTypeEntity);
		articleEntity.setUserVo(uservo);
		if(file.getOriginalFilename()!="") {
			articleEntity.setArticleimg(file.getBytes());
		}

		
		ArticleEntity articleEntity1 = articleService.add(articleEntity);
		if (articleEntity1 != null) {
			redirectAttributes.addFlashAttribute("success", "<" + articleEntity1.getArticletitle() + ">" + "新增成功！");
			redirectAttributes.addFlashAttribute("ignore", "ignore");
		}

		return "redirect:/forum";
	}

	@PostMapping("/context")
	public List<ArticleEntity> findByContext(@RequestParam String context) {
		return articleService.findByS(context);
	}
	
	@GetMapping("/adminTemplate")
	public String adminTemplate() {
		
		return"adminTemplate";
	}

	/*
	 * 註冊尋找文章頁面
	 */
	@GetMapping("/forumSearch")
	public String pageSearch(Model model) {

		return "forumSearch";
	}

	@PostMapping("/search")
	public String articleSearch(Model model, @RequestParam("article_search") String key) {

		if ("".equals(key)) {
			String errmsgs = "請輸入關鍵字";
			model.addAttribute("errmsgs", errmsgs);
			return "forumSearch";
		}
		List<ArticleEntity> articleEntities = articleService.findByWords(key, key);
		if (articleEntities.isEmpty()) {
			String errmsgs = "查無符合條件的資料";
			model.addAttribute("errmsgs", errmsgs);
			return "forumSearch";
		}
		int count = articleEntities.size();
		String total = "符合條件的資料有" + count + "筆資料";
		model.addAttribute("total", total);
		model.addAttribute("articleEntities", articleEntities);
		return "forumSearch";
	}

	/*
	 * 文章列表首頁 獲取全部文章資料 分頁查詢 SIze=5,返回JSON格式 ,目前寫死(2.26) 根據傳入參數決定頁數 page0才是第一頁
	 */
	@ResponseBody
	@GetMapping("/findbypages")
	public Page<ArticleEntity> findAByPages(@RequestParam(defaultValue = "1") int page,
											@RequestParam(defaultValue = "3") int size) {
		Sort sort = Sort.by(Sort.Direction.ASC, "articleid");
		return articleService.findAllByPage(PageRequest.of(page, size, sort));
	}

	@RequestMapping("/forum")
	public String index(@RequestParam(defaultValue = "0") int page,
						@RequestParam(defaultValue = "5") int size,
						Model model) {
		Sort sort = Sort.by(Sort.Direction.DESC, "postdatetime");
		Page<ArticleEntity> pages = articleService.findAllByPage(PageRequest.of(page, size, sort));
		// List<ArticleEntity> articleEntityList =articleService.findAll();
		model.addAttribute("page", pages);

		
		
//		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		String username = null;
		UserVO uservo = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (principal instanceof UserPrincipal) {
			uservo = userService.getUserByEmail(((UserPrincipal) principal).getUsername());
		} 
		
		model.addAttribute("msg","ok");
		model.addAttribute("user", uservo);
		
		List<ArticleEntity> update=articleService.findAll();
//		model.addAttribute("user", uservo); 
		model.addAttribute("update",update);
			return "forum";
		
		
	}
	@GetMapping("/forum/{id}")
	public String articleid(@PathVariable(name="id") Integer id,
							@RequestParam(defaultValue = "0") int page,
							@RequestParam(defaultValue = "10") int size,
							Model model) {
		List<ArticleEntity> list=articleService.findtype(id);
		
		Sort sort = Sort.by(Sort.Direction.DESC, "postdatetime");
		Page<ArticleEntity> pages = articleService.findAllByPage(PageRequest.of(page, size, sort));
		
		UserVO uservo = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (principal instanceof UserPrincipal) {
			uservo = userService.getUserByEmail(((UserPrincipal) principal).getUsername());
		} 
		
		model.addAttribute("user", uservo);
		model.addAttribute("page", pages);
		model.addAttribute("list",list);
		return"forumType";
	}
	
	@GetMapping("/forumaddLike")
	public String like(@RequestParam(name="value",required = true) Integer articleid) {
		ArticleEntity a=articleService.findById(articleid);
		a.setLikecount(a.getLikecount()+1);
		articleService.add(a);
		return "redirect:/read/"+articleid;
	}
	
	@GetMapping("/forumReport")
	public String report(@RequestParam(name="value",required = false) Integer articleid,
							Model model) {
		System.err.println(articleid);
		
		List<ReportTypeEntity> list =reportTypeService.findall();
		
		
		
		model.addAttribute("articleid",articleid);
		model.addAttribute("list",list);
		return "forumReport";
	}
	@GetMapping("/addReport")
	public String addReport (@RequestParam(name="value",required = false) Integer articleid,
							 @RequestParam(name="articleid") Integer id,
							 @RequestParam(name="reporttypeid") Integer reporttypeid,
							 @RequestParam(name="reportdetail") String reportdetail,
							 final RedirectAttributes r) {
		
		UserVO uservo = null;
		UserPrincipal principal1 = (UserPrincipal) SecurityContextHolder.
															getContext().
															getAuthentication().
															getPrincipal();
		uservo = principal1.getUservo();
		ReportEntity report= new ReportEntity();
		
		ArticleEntity articleEntity=articleService.findById(id);
		report.setArticleEntity(articleEntity);
		report.setReportdatetime(new Date());
		report.setUservo(uservo);
		report.setReportdetail(reportdetail);
		report.setStatus(0);
		ReportTypeEntity reportTypeEntity=reportTypeService.findById(reporttypeid);
		report.setReportTypeEntity(reportTypeEntity);
		
		ReportEntity reportEntity =reportService.add(report);
		if(reportEntity!=null) {
			r.addFlashAttribute("report", "我們已收到您對於 <<"+reportEntity.getArticleEntity().getArticletitle()+">>的檢舉,會盡快為您處理");
		}
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(articleEntity.getUserVo().getUseremail());
		mailMessage.setSubject("OGABE系統通知");
		mailMessage.setFrom("tibame105ogabe@gmail.com");
		mailMessage.setText("提醒您,您的文章已遭到檢舉 "+"檢舉詳細原因"+reportdetail);

		emailServices.sendEmail(mailMessage);
		
		
		return "redirect:/forum";
	}
	
	@GetMapping("/likesCountBackstage")
	public String likesCount() {
		
		
		return "forumLikesCount";
	}
	
	
	@GetMapping("/reportBackstage")
	public  String reportBackStage(@RequestParam(defaultValue = "0") int page,
								   @RequestParam(defaultValue = "10") int size,
								   Model model) {
		List<ReportEntity> list=reportService.findall();
		
		Sort sort = Sort.by(Sort.Direction.DESC, "reportid");
		Page<ReportEntity> pages = reportService.findAllByPage(PageRequest.of(page, size, sort));
		model.addAttribute("page", pages);
		model.addAttribute("list", list);
		return "forumReportBackStage";
	}
	
	@GetMapping("/forumBackStage")
	public String forumBS(Model model) {
		
		List<ArticleEntity> list=articleService.findHotArticle();
		List<ArticleEntity> views=articleService.findViews();
		
		model.addAttribute("list", list);
		model.addAttribute("views",views);
		
		
		return"forumBackStage";
	}
	@GetMapping("/recommend/{id}")
	public String recommend(@PathVariable(name="id") Integer id,
							HttpSession session) {
		
		ArticleEntity articleEntity=articleService.findById(id);
		if(articleEntity!=null) {
			
			session.setAttribute("article", articleEntity);
		}
		
		return"redirect:/forum";
	}
	@GetMapping("/forumReportCheck")
	public String reportcheck(@RequestParam(name="value",required = true) Integer value,
								Model model) {
		
		ArticleEntity articleEntity=articleService.findById(value);
		model.addAttribute("articleObj",articleEntity );
		
		
		return "forumReportCheck";
	}
	
	@GetMapping("/reportCheck")
	public String check(@RequestParam(name="reportid") Integer reportid,
				   @RequestParam(name="articleid")Integer articleid,
				   @RequestParam(name="administrator") String a,
				   @RequestParam(name="result",required = true) Integer result,
				   @RequestParam(name="check_result") String resultDetail,
				   final RedirectAttributes r) {
		ReportEntity reportEntity=reportService.findOne(reportid);
		ArticleEntity articleEntity=articleService.findById(articleid);
		if(reportEntity!=null) {
			
			reportEntity.setStatus(result);
			reportEntity.setSign(a);
			reportEntity.setRemark(resultDetail);
			reportEntity.setSubmitdatetime(new Date());
			articleEntity.setActivestatusid(2);
			
			reportService.add(reportEntity);
			articleService.add(articleEntity);
			
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(reportEntity.getUservo().getUseremail());
			mailMessage.setSubject("OGABE系統通知");
			mailMessage.setFrom("tibame105ogabe@gmail.com");
			if(result==1) {
			mailMessage.setText("提醒您,您檢舉的文章已審核完畢,已將該文章下架");
			System.err.println("---------------------");
			}
			if(result==2) {
				mailMessage.setText("提醒您,您檢舉的文章已審核完畢,檢舉不成立的原因 :"+resultDetail);
				System.err.println("---------------------");
			}
			emailServices.sendEmail(mailMessage);
			r.addFlashAttribute("administrator", r);
			r.addFlashAttribute("checkresult",resultDetail);
			return "redirect:/reportBackstage";
		}

		
		return "redirect/reportBackstage";
	}
	
	@ResponseBody
	@GetMapping("/jpql")
	public List<ArticleEntity> findByJPQL(@RequestParam int length) {
		return articleService.findByJPQL(length);
	}

	@ResponseBody
	@GetMapping("/update")
	public int findByJPql(@RequestParam String articletitle, 
						  @RequestParam String articlecontext,
			@RequestParam Integer articleid) {
		return articleService.findByJPQL(articletitle, articlecontext, articleid);
	}

	@GetMapping("/index")
	public String index() {

		return "index";
	}

	@ResponseBody
	@GetMapping("/addtype")
	public void add() {
		ArticleEntity articleEntity = new ArticleEntity();

		ArticleTypeEntity articleTypeEntity = articleTypeService.find(3);

		articleEntity.setArticleTypeEntity(articleTypeEntity);

		articleEntity.setArticletitle("springboot");
		articleEntity.setArticlecontext("fun");


		articleService.add(articleEntity);

		String a = articleEntity.getArticleTypeEntity().getArticletypedetail();
		System.out.println(a);
	}

	@GetMapping("/ok")
	public ArticleEntity findReply() {

		ArticleEntity articleEntity = articleService.findById(2);
		
		return articleEntity;

	}

	@GetMapping("/pp")
	public String ok() {

		return "pra";
	}

	@GetMapping("/comments")
	public String add(@RequestParam("replycontext") String context, 
					  @RequestParam("articleid") Integer id,
			final RedirectAttributes ra) {

		ArticleEntity articleEntity = articleService.findById(id);
		ReplyEntity replyEnity = new ReplyEntity();
		replyEnity.setReplycontext(context);
		replyEnity.setArticleEntity(articleEntity);
		UserVO uservo = null;
		UserPrincipal principal1 = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		uservo = principal1.getUservo();
		replyEnity.setUserVo(uservo);

		ReplyEntity r = replyService.add(replyEnity);

		if (r != null) {
			ra.addFlashAttribute("commentSuccess", "您對<" + r.getArticleEntity().getArticletitle() + ">本文章的留言新增成功！");
		}

		return "redirect:/forum";

//       測試聯表
//        String a=replyEnity.getArticleEntity().getArticlecontext();
//        System.out.println(a);
	}

	@GetMapping("/forumCollection")
	public String collect(Model model) {
		UserVO uservo = null;
		UserPrincipal principal1 = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		uservo = principal1.getUservo();
		CollectionEntity c = new CollectionEntity();
		Integer id = uservo.getUserid();
		List<CollectionEntity> list = collectionService.find(id);

//		List<CollectionEntity> list=collectionService.find(uservo);

		model.addAttribute("list", list);
		return "forumCollection";
	}

	@GetMapping("/addCollect")
	public String addCollect(@RequestParam("articleid") Integer id,
							final RedirectAttributes r) {

		ArticleEntity ae = articleService.findById(id);
		CollectionEntity c = new CollectionEntity();
		c.setArticleEntity(ae);
		UserVO uservo = null;
		UserPrincipal principal1 = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		uservo = principal1.getUservo();
		c.setUservo(uservo);
		CollectionEntity colll=collectionService.add(c);
		if(colll!=null) {
			r.addFlashAttribute("success","您以成功將<<"+ae.getArticletitle()+">>加入收藏區 !");
			
		}


		return "redirect:/forumCollection";
	}	
		@GetMapping("/cancel/{cid}")
		public String cancel(@PathVariable("cid") Integer id) throws SQLException{
			
		collectionService.cancel(id);
		
		return"redirect:/forumCollection";
		}
	
		@GetMapping("/image/displays/{articleid}")
		public void showImg(@PathVariable Integer articleid, HttpServletResponse res, ArticleEntity articleEntity)
		throws ServletException, IOException {
		System.out.println("---------------"+articleid);
		ArticleEntity a = articleService.findById(articleid);
		res.setContentType("image/jped, image/jpg, image/png, image/gif");
		res.getOutputStream().write(a.getArticleimg());
		res.getOutputStream().close();

	}	
		
		@GetMapping("/welcome") 
		public String welcome(@RequestParam(defaultValue = "0") int page,
							  @RequestParam(defaultValue = "5") int size,
							  Model model) {
			
			
		return "weicome";
	}
		
		@GetMapping("/welcomeAuth") 
		public String welcomeAuth() {
			
			UserVO uservo = null;
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserPrincipal) {
				uservo = ((UserPrincipal) principal).getUservo();
				return "redirect:/forum";
			} else {
				return "redirect:/forumGuest";
			}

		}
		@GetMapping("/forumGuest")
		public String Guest(@RequestParam(defaultValue = "0") int page,
							@RequestParam(defaultValue = "10") int size,
							Model model) {
			Sort sort = Sort.by(Sort.Direction.DESC, "postdatetime");
			Page<ArticleEntity> pages = articleService.findAllByPage(PageRequest.of(page, size, sort));
			// List<ArticleEntity> articleEntityList =articleService.findAll();
			model.addAttribute("page", pages);
			return "forumGuest";
		}
//		@ResponseBody
		@GetMapping("/delete/{id}")
		public String deleteReply(@PathVariable("id") Integer replyid,
								  @RequestParam("aid")Integer articleid,
								  final RedirectAttributes redirectAttributes) {
			ReplyEntity r=replyService.findOne(replyid);
			replyService.deleteReply(replyid);
			System.out.println(articleid);
			redirectAttributes.addFlashAttribute("deleteReply","提醒您,您以成功刪除 <"+r.getReplycontext()+">這則留言");
			
			return "redirect:/read/"+articleid;

				
		}
		@GetMapping("forumMine")
		public String my(Model model) {
			UserVO uservo = null;
			UserPrincipal principal1 = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			uservo = principal1.getUservo();
			Integer u=uservo.getUserid();
			List<ArticleEntity> list=articleService.findUserArticle(u);
//			UserVO user=userService.getUserById(u);
//			List<ArticleEntity> a=uservo.getArticleEntities();
			model.addAttribute("list",list);
			return "forumUserArticle";
		}
		@GetMapping("/update/{id}")
		public String update(@PathVariable("id") Integer articleid,
								final RedirectAttributes rd) {
			
			ArticleEntity articleEntity=articleService.findById(articleid);
			if(articleEntity.getArticleimg()==null) {
				articleEntity.setArticleimg(null);
			}
			rd.addFlashAttribute("articleEntity",articleEntity);
			
			return "redirect:/updates";
		}
		@GetMapping("/updates")
		public String updatecheck(Model model) {
			List<ArticleTypeEntity> articleEntityList = articleTypeService.findAll();
			model.addAttribute("articleall", articleEntityList);
			return "forumUpdate";
			
		}
		
		
		@PostMapping("/articleupdate/{id}")
		public String articleupdate(@PathVariable("id") Integer articleid,
									@RequestParam ("articletitle") String title,
									@RequestParam("articletypeid") Integer typeid,
									@RequestParam("articlecontext") String context, 
									@RequestParam(required = false) MultipartFile file)
									throws IOException{ 
			ArticleEntity articleEntity =articleService.findById(articleid);
			ArticleTypeEntity articleTypeEntity=articleTypeService.find(typeid);
			articleEntity.setArticlecontext(context);
			articleEntity.setArticletitle(title);
			articleEntity.setArticleTypeEntity(articleTypeEntity);
			articleEntity.setUpdatetime(new Date());
			
			
			if(file!=null || file.getOriginalFilename()!="") {
			articleEntity.setArticleimg(file.getBytes());
			}
			articleService.add(articleEntity);
			System.out.println("-------------------更新成功");
			return "redirect:/forum";

			
		}
}
