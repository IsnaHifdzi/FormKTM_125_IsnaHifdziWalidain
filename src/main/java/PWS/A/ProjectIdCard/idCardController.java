/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PWS.A.ProjectIdCard;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author acer
 */
@Controller
public class idCardController {
    @RequestMapping("/getKtm")
    public String getData(@RequestParam("nama") String nama,
                          @RequestParam("tanggal")@DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                          @RequestParam("nim")String nim,
                          @RequestParam("jurusan") String jurusan,
                          
                          @RequestParam("gambar") MultipartFile image,
                          Model model) throws IOException{
        
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy");
        String tanggal = sdf.format(date);
        
        String blob = Base64.encodeBase64String(image.getBytes());
        String gambar = "data:image/jpeg;base64,".concat(blob);
        
        model.addAttribute("nama", nama);
        model.addAttribute("nim", nim);
        model.addAttribute("jurusan", jurusan);
        
        model.addAttribute("tanggal", tanggal);
        model.addAttribute("gambar", gambar);
        
        return "view";
    }
    
}
