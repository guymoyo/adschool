package adschool.web;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import adschool.domain.Parent;
import adschool.domain.Pays;
import adschool.utils.FileUploadService;

@RooWebScaffold(path = "parents", formBackingObject = Parent.class)
@RequestMapping("/parents")
@Controller
public class ParentController {

	@ModelAttribute("economat")
	public String populateMenu() {
		return "block";
	}

	@RequestMapping(value="/findParents/ByAjax", method = RequestMethod.GET)
	@ResponseBody
	public String findProductByCipAjax(Model uiModel ,  HttpServletRequest httpServletRequest) {
		String nomPere2 = httpServletRequest.getParameter("nompere");
		String nomMere2 = httpServletRequest.getParameter("nommere");
		List<Parent> listParent = Parent.findParentsByNomPereLikeOrNomMereLike(nomPere2, nomMere2).getResultList();
		return Parent.toJsonArray(listParent);
	}

	@RequestMapping(value="/getParentByAjax/{parentId}", method = RequestMethod.GET)
	@ResponseBody
	public String getStudentByAjax(@PathVariable("parentId") Long parentId,Model uiModel ,  HttpServletRequest httpServletRequest) {
		return Parent.findParent(parentId).toJson();
	}

	@RequestMapping(value="/saveParentByAjax", method = RequestMethod.GET)
	@ResponseBody
	public String saveParentByAjax(@Valid Parent parent, BindingResult bindingResult,Model uiModel ,  HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) return null;
		parent.setNationalite(Pays.findPays(new Long(1)));
		parent.persist();
		return parent.toJson();
	}
	
	


	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid Parent parent, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		System.out.println("in create");
		//parent.validate(bindingResult);
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("parent", parent);
			addDateTimeFormatPatterns(uiModel);
			return "parents/create";
		}
		if (parent.getId()==null) parent.persist();
	/*	MultipartFile file=   parent.getUserImage();
		try {
			String filePath =  FileUploadService.storeFile(file,FileUploadService.IMAGES_PARENT_DIR,parent.getMatricule());
			if (filePath == null) {
				if(parent.getPathPhoto().isEmpty()){
					parent.setPathPhoto(FileUploadService.getDefaultImage(parent));
				}
				
			} else {
				
				parent.setPathPhoto(filePath);
			}
			
		
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		uiModel.asMap().clear();
		
			parent.merge();
		return "redirect:/parents/" + encodeUrlPathSegment(parent.getId().toString(), httpServletRequest);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String update(@Valid Parent parent, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		System.out.println("in update");
		parent.validate(bindingResult);
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("parent", parent);
			addDateTimeFormatPatterns(uiModel);
			return "parents/update";
		}
		if(StringUtils.isNotBlank(parent.getPathPhoto()))FileUploadService.deleteFiles(parent.getPathPhoto());
		MultipartFile file = parent.getUserImage();
		try {
			String filePath =  FileUploadService.storeFile(file,FileUploadService.IMAGES_PARENT_DIR,parent.getNom());
			if (filePath == null) {
				parent.setPathPhoto(FileUploadService.getDefaultImage(parent));
			} else {
				parent.setPathPhoto(filePath);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		uiModel.asMap().clear();
		parent.merge();
		return "redirect:/parents/" + encodeUrlPathSegment(parent.getId().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		Parent parent = Parent.findParent(id);
		if(parent != null && StringUtils.isNotBlank(parent.getPathPhoto())) FileUploadService.deleteFiles(parent.getPathPhoto());
		if(parent != null) parent.remove();
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/parents";
	}
	
	  public static TypedQuery<Parent> findParentsByNomPereLikeOrNomMereLike(String nomPere, String nomMere) {
	        if (nomPere == null || nomPere.length() == 0) throw new IllegalArgumentException("The nomPere argument is required");
	        nomPere = nomPere.replace('*', '%');
	        if (nomPere.charAt(0) != '%') {
	            nomPere = "%" + nomPere;
	        }
	        if (nomPere.charAt(nomPere.length() - 1) != '%') {
	            nomPere = nomPere + "%";
	        }
	        if (nomMere == null || nomMere.length() == 0) {
	        	
	        	nomMere = "";
	        /*    nomMere = nomMere.replace('*', '%');
	            if (nomMere.charAt(0) != '%') {
	                nomMere = "%" + nomMere;
	            }
	            if (nomMere.charAt(nomMere.length() - 1) != '%') {
	                nomMere = nomMere + "%";
	            }*/
	        	
	        	
	        }
	        

	        
	        //throw new IllegalArgumentException("The nomMere argument is required");

	        EntityManager em = Parent.entityManager();
	        TypedQuery<Parent> q = em.createQuery("SELECT o FROM Parent AS o WHERE LOWER(o.nomPere) LIKE LOWER(:nomPere)  OR LOWER(o.nomMere) LIKE LOWER(:nomMere)", Parent.class);
	        q.setParameter("nomPere", nomPere);
	        q.setParameter("nomMere", nomMere);
	        return q;
	    }
}
