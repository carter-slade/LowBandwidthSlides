import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class image_compare{
   BufferedImage image1,image2;
   int width;
   int height;
   
   public image_compare()throws IOException {
      image1=null;
      image2=null;
      width=0;
      height=0;
      
   }
   public boolean parseimg(String s1,String s2)
   {
	s1="images/"+s1;
	
	s2="images/"+s2;
       try {
         File input1 = new File(s1);
         File input2 = new File(s2);
         image1 = ImageIO.read(input1);
         image2 = ImageIO.read(input2);
         width = Math.min(image1.getWidth(),image2.getWidth());
         height = Math.min(image1.getHeight(),image2.getHeight());
         int stat=0;
         int count=0;
         for(int i=0; i<height; i++){
         	
            for(int j=0; j<width; j++){
                count++;
                //System.out.println(count);
               Color c1 = new Color(image1.getRGB(j, i));
               Color c2 = new Color(image2.getRGB(j, i));
               if(Math.abs(c1.getRed()-c2.getRed())>=10 || Math.abs(c1.getBlue()-c2.getBlue())>=10 || Math.abs(c1.getGreen()-c2.getGreen())>=10)
               {
                   stat++;
                   //System.out.print("Mismatch");
                   //System.out.println(c1.getRed()+" "+c2.getRed());
                   //System.out.println(c1.getBlue()+" "+c2.getBlue());
                   //System.out.println(c1.getGreen()+" "+c2.getGreen());
                   //if(stat==100)
                   //break;
                }
            }
         }
         //System.out.println("Mismatches = "+stat);
         //System.out.println("Total = "+count);
         double percent = ((double)(stat*100)/(double)count);
         
         /*System.out.print(percent+" ");
         for(int i= 0;i<=percent;i++)
         {
             System.out.print("--");
            }
            System.out.println(); */ 
            
            //System.out.println("Percentage = "+ percent);
         if(percent>=5)
         return false;
      } catch (Exception e) {
        //System.out.println(e.getMessage());
        System.exit(0);
            }
        return true;
    }
   
   static public void main(String args[]) throws Exception 
   {
      image_compare obj = new image_compare();
      int count=0;
      String use="",usenext="";
      int stat=1;
      String file1 = "",file2 = "0000001";
      String filename1="",filename2="";
      boolean t;
      int buff=1;
      for(int i=1;i<5000;i++)
      {
          use = ""+i;
          usenext = ""+(i+1);
          while(use.length()!=7)
          use="0"+use;
          while(usenext.length()!=7)
          usenext="0"+usenext;
          file1=use;
          file2=usenext;
          filename1="image-"+file1+".jpg";
          filename2="image-"+file2+".jpg";
          //System.out.print(i + "&"+(i+1)+">>");
          //System.out.println(filename1);
          t = obj.parseimg(filename1,filename2);	
          //System.out.println(t);
          if(t==false)
          {
              if(count==0)
              continue;
              else
              count=0;
            }
          if(t==true)
          count++;
          t=false;
          
          if(count==2)
          {     
              if(i>=3 && buff>0)
          {
              use = ""+(i-1);
              while(use.length()!=7)
              use="0"+use;
              filename1="image-"+(use)+".jpg";
              use = ""+(buff);
              while(use.length()!=7)
              use="0"+use;
              filename2="image-"+(use)+".jpg";
              t=obj.parseimg(filename1,filename2);
          }
          if(t==false)
              {System.out.println((i-2));buff=i-1;}
          }
          
      }
      
   }
   public String convert(int n)
   {
       String temp="";
       temp=temp+n/60+":"+n%60;
       return temp;
    }
}
