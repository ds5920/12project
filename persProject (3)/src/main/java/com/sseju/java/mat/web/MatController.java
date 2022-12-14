package com.sseju.java.mat.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sseju.java.code.service.CodeService;
import com.sseju.java.code.service.CodeVO;
import com.sseju.java.company.service.CompanyService;
import com.sseju.java.company.service.CompanyVO;
import com.sseju.java.employee.service.EmployeeService;
import com.sseju.java.employee.service.EmployeeVO;
import com.sseju.java.mat.service.MatService;
import com.sseju.java.mat.service.MatVO;
import com.sseju.java.workorder.serivce.WorkOrderService;
import com.sseju.java.workorder.serivce.WorkOrderVO;

@Controller
public class MatController {

	@Autowired
	MatService service;

	@Autowired
	CodeService cservice;

	@Autowired
	CompanyService comservice;

	@Autowired
	EmployeeService empservice;
	
	@Autowired
	WorkOrderService woService;

//	@GetMapping("/matOrd")
//	@ResponseBody
	@GetMapping("/matOrd")
	public String matOrd() {
		//model.addAttribute(null, model);
		return "/admin/mat/matOrd";
	}

	@ResponseBody
	@GetMapping("/emp")
	public List<EmployeeVO> getEmpList() {
		return empservice.getEmpList();
	}

	@ResponseBody
	@GetMapping("/material")
	public List<MatVO> material() {
		return service.material();
	}

	@ResponseBody
	@GetMapping("/company")
	public List<CompanyVO> getCompanyList() {
		return comservice.getCompanyList();
	}

	@ResponseBody
	@GetMapping
	public List<CodeVO> whList() {
		return cservice.whList();
	}

	// ?????? ?????? ?????????
	@ResponseBody
	@GetMapping("/matordList")
	public List<MatVO> matordList() {
		return service.matordList();
	}
	
	@ResponseBody
	@GetMapping("/prtOrdList")
	public List<MatVO> prtOrdList(){
		return service.prtOrdList();
	}
	
	@ResponseBody
	@PostMapping("/infoModal")
	public List<MatVO> infoModal (@RequestBody MatVO MatVO) {
		return service.infoModal(MatVO);
	}

	@ResponseBody
	@PostMapping("/matOrdModal")
	public List<MatVO> matOrdModal
		(@RequestBody MatVO MatVO, HttpSession session) {
		
		List<MatVO> mvo = service.matOrdModal(MatVO);
		String empId = ((EmployeeVO)session.getAttribute("user")).getEmpId();
		System.out.println("??????" + session.getAttribute("user"));
		for(int i = 0; i < mvo.size(); i++) {   
			mvo.get(i).setMatOrdEmp(empId);
		}
		       
		return mvo;
	}
	// ?????? ??????
	/*
	 * @ResponseBody
	 * 
	 * @PostMapping("/insertMatbuy") public int insertMatbuy(@RequestParam(value =
	 * "Qty[]", required = false) List<String> Qty,
	 * 
	 * @RequestParam(value = "mc[]", required = false) List<String> mc,
	 * 
	 * @RequestParam(value = "cd[]", required = false) List<String> cd,
	 * 
	 * @RequestParam(value = "mp[]", required = false) List<String> mp,
	 * 
	 * @RequestParam(value = "moe[]", required = false) List<String> moe) { int
	 * result = 0;
	 * 
	 * for (int i = 0; i < mc.size(); i++) { MatVO vo = new MatVO();
	 * vo.setMatOrdQty(Integer.valueOf(Qty.get(i))); vo.setMatCode(mc.get(i));
	 * vo.setCpCode(cd.get(i)); vo.setMatPrice(mp.get(i));
	 * vo.setMatOrdEmp(moe.get(i));
	 * 
	 * result += service.insertMatbuy(vo); } return result; }
	 */
	
	@ResponseBody
	@PostMapping("/insertMatbuy")
	public int insertMatbuy(@RequestBody List<MatVO> vo) {
		
		System.out.println("??????????????????" + vo);
		int result = 0;
		
		for (int i = 0; i < vo.size(); i++) { 
			result += service.insertMatbuy(vo.get(i)); 
		 }
		
		return result; 
	}
	
	@ResponseBody
	@PostMapping("/updateordYn")
	public int updateordYn(@RequestBody List<MatVO> vo) {
		System.out.println("???????????????????");
		System.out.println("????????????" + vo);
		int result = 0;
		
		for (int i = 0; i < vo.size(); i++) { 
			result += service.updateordYn(vo.get(i));
		}
		return result;
		}

	// ?????? ??????
	@ResponseBody
	@PostMapping("/updateMatbuy")
	public int updateMatbuy(
			@RequestParam(value = "mon[]", required = false) List<String> mon,
			@RequestParam(value = "Qty[]", required = false) List<String> Qty,
			@RequestParam(value = "mc[]", required = false) List<String> mc,
			@RequestParam(value = "cd[]", required = false) List<String> cd,
			@RequestParam(value = "mp[]", required = false) List<String> mp,
			@RequestParam(value = "moe[]", required = false) List<String> moe,
			@RequestParam(value = "yn[]", required = false) List<String> yn) {
		int uM = 0;
		System.out.println(mon.size());
		for (int i = 0; i < mon.size(); i++) {
			MatVO vo = new MatVO();
			vo.setMatOrdNo(mon.get(i));
			vo.setMatOrdQty(Integer.valueOf(Qty.get(i)));
			vo.setMatCode(mc.get(i));
			vo.setCpCode(cd.get(i));
			vo.setMatPrice(mp.get(i));
			vo.setMatOrdEmp(moe.get(i));
			vo.setMatOrdYn(yn.get(i));

			System.out.println(vo.getMatOrdYn());
			System.out.println(yn.get(i));

			vo.setMatInQty(Integer.valueOf(Qty.get(i)));

			uM += service.updateMatbuy(vo);

			// if(vo.getMatOrdYn() == "??????") {
			if (vo.getMatOrdYn().equals("??????")) {
				MatVO vo1 = new MatVO();
				// List<MatVO> list = new ArrayList<>();
				System.out.println("??????");
				System.out.println(vo.getMatOrdYn());
				System.out.println(vo.getMatOrdNo());
				vo1 = service.selectMatordno(vo);

				if (vo1 != null) {
					service.updateMatnotIn(vo);
				} else {
					service.insertMatord(vo);
				}
			}

			/*
			 * update -> Yn??? ????????? ???????????? ?????????????????? ???????????? insertMatord ??????????????? ?????? ?????????????????? -> Yn??? '??????'???
			 * update????????? ?????? ?????????/ '??????'??? ???????????? ?????????????????? ??????????????? ??????.
			 * 
			 * update -> Yn??? ????????? ????????? Insert -> mat_chk??? ??????????????? ?????? mat_chk??? mat_ord_no??? ??????
			 * ???????????? ????????? insert??? ?????? update??? ???????????? ?????? ????????? insert??? ?????????
			 * 
			 * 
			 * 
			 */

		}

		System.out.println(uM);
		return uM;
	}

	// ?????? ??????
	@PostMapping("/deleteMat")
	@ResponseBody
	public int selectDelete(@RequestBody MatVO vo) {
		return service.deleteMatbuy(vo.getDelno());
	}

	// ????????????
	@GetMapping("/matIn")
	public String matIn(Model model) {
		// model.addAttribute("mat", model)
		return "/admin/mat/matIn";
	}

	// ?????? ?????? ?????????
	@ResponseBody
	@GetMapping("/matInList")
	public List<MatVO> matInList() {
		return service.matInList();
	}
	
	//?????? ??????
	@ResponseBody
	@PostMapping("/updateMat")
	public int updateMat(@RequestBody List<MatVO> vo) {
		int a = 0;
		
		for(int i=0; i < vo.size(); i++) {
			a += service.updateMat(vo.get(i));
		}
		return a;
	}

	// ?????????
	@GetMapping("/matNotin")
	public String matNotin() {
		// model.addAttribute("mat", model)
		return "/admin/mat/matNotin";
	}

	// ????????? ?????????
	@ResponseBody
	@GetMapping("/matNotinList")
	public List<MatVO> matNotinList() {
		// model.addAttribute("mat", model)
		return service.matNotinList();
	}

	// ????????? => LOT ??????

	/*
	@ResponseBody
	@PostMapping("/insertLot")
	public int insertLot(@RequestParam(value = "lotDate[]", required = false) List<Date> lotDate,
			@RequestParam(value = "mpCode[]", required = false) List<String> mpCode,
			@RequestParam(value = "whCode[]", required = false) List<String> whCode,
			@RequestParam(value = "lotQty[]", required = false) List<String> lotQty,
			@RequestParam(value = "matChkCode[]", required = false) List<String> matChkCode,
			@RequestParam(value = "matPassQty[]", required = false) List<String> matPassQty,
			@RequestParam(value = "matOrdNo[]", required = false) List<String> matOrdNo,
			@RequestParam(value = "matOrdYn[]", required = false) List<String> matOrdYn) {

		int result = 0;
		System.out.println(matOrdNo.size());

		for (int i = 0; i < matOrdNo.size(); i++) {
			System.out.println("????????? ??????");
			MatVO vo = new MatVO();
			vo.setLotDate((lotDate.get(i)));
			vo.setMpCode(mpCode.get(i));
			vo.setWhCode(whCode.get(i));
			vo.setLotQty(Integer.valueOf(lotQty.get(i)));
			vo.setMatChkCode(matChkCode.get(i));

			result += service.insertLot(vo);
			
			

			// if(vo.getMatOrdYn() == "??????") {
			if (vo.getMatOrdYn().equals("??????")) {
//				vo.setMatErrQty(i)
				vo.setMatPassQty(Integer.valueOf(matPassQty.get(i)));
				vo.setMatErrQty(Integer.valueOf(matPassQty.get(i)));
				vo.setMatOrdNo(matOrdNo.get(i));
				// List<MatVO> list = new ArrayList<>();
				System.out.println("??????");
				System.out.println(vo.getMatOrdYn());
				System.out.println(vo.getMatOrdNo());

				service.updateChk(vo);
				// ????????? ?????? matInQty??? 3%??? ?????????????????? ???????????? PassQty??? ???????????? ??????
				
				//if(insert[i].matOrdYn.equals("??????")){
				//if(insert[i].matOrdYn == "??????"){
			}

		}

		return result;

	}*/

	@ResponseBody
	@PostMapping("/insertLot")
	public int insertLot(@RequestBody List<MatVO> vo) {
		int a = 0;
		
//		vo.get(mat)
		for (int i = 0; i<vo.size(); i++) {
			System.out.println("????????????????");
			System.out.println(vo.get(i));
			a += service.insertLot(vo.get(i));
			a += service.updateMoy(vo.get(i));
			a += service.updateChk(vo.get(i));
			a += service.insertErr(vo.get(i));
		}		
		return a;
	}
	
	@ResponseBody
	@PostMapping("/updateMatnotIn")
	public int updateMatnotIn(@RequestBody List<MatVO> vo) {
		int a = 0;

		for (int i = 0; i < vo.size(); i++) {
			System.out.println(vo.get(i));
			a += service.updateMatnotIn(vo.get(i));
			
		}
		return a;
	}
	
	@ResponseBody
	@PostMapping("/deleteMatnotin")
	public int deleteMatnotin(@RequestBody MatVO vo) {
		int a = 0;
			
		a += service.deleteMatbuy(vo.getDelno());
		a += service.deleteMat(vo.getDelno());
		
		return a;
	}
	
	@GetMapping("/matOut")
	public String matOut() {
		return "/admin/mat/matOut";
	}
	
	//?????? ?????? ?????????
	@ResponseBody
	@GetMapping("/matOutList")
	public List<MatVO> matOutList(){
		return service.matOutList();
	}

	@RequestMapping("/matStock")
	public String matt() {
		return "/admin/mat/matStock";
	}
	
	//?????? ?????? ?????????
	@ResponseBody
	@GetMapping("/matStockList")
	public List<MatVO> matStockList(){
		return service.matStockList();
	}
	
	@GetMapping("/matRecChk")
	public String matRecChkList() {
		return "/admin/quamanage/matRecChk";
	}
	
	//?????? ?????? ?????????
	@ResponseBody
	@GetMapping("/matCheckList")
	public List<MatVO> matCheckList(){
		return service.matCheckList();
	}

	@PostMapping("docUpdate")
	public String docUpdate(MatVO MatVO) {
		System.out.println("??????");
		System.out.println(MatVO.getMatChkCode());
		System.out.println(MatVO.getMatErrDoc());
		service.docUpdate(MatVO);
		return "redirect:/matRecChk";
	}
	
	@ResponseBody
	@GetMapping("/errModal")
	public List<MatVO> errCode(){
		return service.errCode();
	}
}
