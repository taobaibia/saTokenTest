//package com.yeweiyang.token;
//
//
//import javax.imageio.ImageIO;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.*;
//import java.net.URL;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
///*******************************************************************************
// * Description: 图片水印工具类
// * @author zengshunyao
// * @version 1.0
// */
//public class ImageRemarkUtilTest {
//
//    // 水印透明度
//    private static float alpha = 0.2f;
//    // 水印横向位置
//    private static int positionWidth = 0;
//    // 水印纵向位置
//    private static int positionHeight = 0;
//    // 水印文字字体
//    private static Font font = new Font("宋体", Font.BOLD, 20);
//    // 水印文字颜色
//    private static Color color = new Color(229, 230, 231);;
//
//    /**
//     *
//     * @param alpha
//     *            水印透明度
//     * @param positionWidth
//     *            水印横向位置
//     * @param positionHeight
//     *            水印纵向位置
//     * @param font
//     *            水印文字字体
//     * @param color
//     *            水印文字颜色
//     */
//    public static void setImageMarkOptions(float alpha, int positionWidth,
//                                           int positionHeight, Font font, Color color) {
//        if (alpha != 0.0f)
//            ImageRemarkUtilTest.alpha = alpha;
//        if (positionWidth != 0)
//            ImageRemarkUtilTest.positionWidth = positionWidth;
//        if (positionHeight != 0)
//            ImageRemarkUtilTest.positionHeight = positionHeight;
//        if (font != null)
//            ImageRemarkUtilTest.font = font;
//        if (color != null)
//            ImageRemarkUtilTest.color = color;
//    }
//
//    /**
//     * 给图片添加水印图片
//     *
//     * @param iconPath
//     *            水印图片路径
//     * @param srcImgPath
//     *            源图片路径
//     * @param targerPath
//     *            目标图片路径
//     */
//    public static void markImageByIcon(String iconPath, String srcImgPath,
//                                       String targerPath) {
//        markImageByIcon(iconPath, srcImgPath, targerPath, null);
//    }
//
//    /**
//     * 给图片添加水印图片、可设置水印图片旋转角度
//     *
//     * @param iconPath
//     *            水印图片路径
//     * @param srcImgPath
//     *            源图片路径
//     * @param targerPath
//     *            目标图片路径
//     * @param degree
//     *            水印图片旋转角度
//     */
//    public static void markImageByIcon(String iconPath, String srcImgPath,
//                                       String targerPath, Integer degree) {
//        OutputStream os = null;
//        try {
//
//            Image srcImg = ImageIO.read(new File(srcImgPath));
//
//            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),
//                    srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);
//
//            // 1、得到画笔对象
//            Graphics2D g = buffImg.createGraphics();
//
//            // 2、设置对线段的锯齿状边缘处理
//            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
//                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//
//            g.drawImage(
//                    srcImg.getScaledInstance(srcImg.getWidth(null),
//                            srcImg.getHeight(null), Image.SCALE_SMOOTH), 0, 0,
//                    null);
//            // 3、设置水印旋转
//            if (null != degree) {
//                g.rotate(Math.toRadians(degree),
//                        (double) buffImg.getWidth() / 2,
//                        (double) buffImg.getHeight() / 2);
//            }
//
//            // 4、水印图片的路径 水印图片一般为gif或者png的，这样可设置透明度
//            ImageIcon imgIcon = new ImageIcon(iconPath);
//
//            // 5、得到Image对象。
//            Image img = imgIcon.getImage();
//
//            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
//                    alpha));
//
//            // 6、水印图片的位置
//            g.drawImage(img, positionWidth, positionHeight, null);
//            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
//            // 7、释放资源
//            g.dispose();
//
//            // 8、生成图片
//            os = new FileOutputStream(targerPath);
//            ImageIO.write(buffImg, "JPG", os);
//
//            System.out.println("图片完成添加水印图片");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (null != os)
//                    os.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    /**
//     * 给图片添加水印文字
//     *
//     * @param logoText
//     *            水印文字
//     * @param srcImgPath
//     *            源图片路径
//     * @param targerPath
//     *            目标图片路径
//     */
//    public static void markImageByText(String logoText, String srcImgPath,
//                                       String targerPath) {
//        markImageByText(logoText, srcImgPath, targerPath, null);
//    }
//
//    /**
//     * 给图片添加水印文字、可设置水印文字的旋转角度
//     *
//     * @param logoText
//     * @param srcImgPath
//     * @param targerPath
//     * @param degree
//     */
//    public static void markImageByText(String logoText, String srcImgPath,
//                                       String targerPath, Integer degree) {
//
//        InputStream is = null;
//        OutputStream os = null;
//        try {
//            //File file = ImageRemarkUtil.getFile(srcImgPath);
//
//            // 1、源图片
//            Image srcImg = ImageIO.read(new File(srcImgPath));
//            //Image srcImg = ImageIO.read(file);
//
//            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),
//                    srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);
//
//            // 2、得到画笔对象
//            Graphics2D g = buffImg.createGraphics();
//            // 3、设置对线段的锯齿状边缘处理
//            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
//                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//            g.drawImage(
//                    srcImg.getScaledInstance(srcImg.getWidth(null),
//                            srcImg.getHeight(null), Image.SCALE_SMOOTH), 0, 0,
//                    null);
//
//
//            int font = 20;
//            int size = 2;
//            if(buffImg.getWidth() > buffImg.getHeight()){
//                size = 1;
//                font = imageFont(font,buffImg);
//            } else {
//                font = buffImg.getWidth(null)/300*20;
//                font = 30;
//            }
//            System.out.println(size + ";" + font);
//
//
//
//            // 4、设置水印旋转
//            if (null != degree) {
////                g.rotate(Math.toRadians(degree),
////                        (double) buffImg.getWidth(),
////                        (double) buffImg.getHeight()*5);
//                g.rotate(Math.toRadians(degree),
//                        (double) buffImg.getWidth(),
//                        (double) buffImg.getHeight()*size);
//            }
//            // 5、设置水印文字颜色
//            g.setColor(color);
//            // 6、设置水印文字Font
//            g.setFont(new Font("宋体", Font.BOLD, font));
//            //g.setFont(new Font("宋体", Font.BOLD,buffImg.getWidth(null)/300*20));
//
//            // 7、设置水印文字透明度
//            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
//                    alpha));
//
//
//            System.out.println(buffImg.getWidth() + "; " + buffImg.getHeight());
//            // 8、第一参数->设置的内容，后面两个参数->文字在图片上的坐标位置(x,y)
//            int num = 0;
//            for(int i=1;i<size*10;i++) { // 平铺水印（需要设置对称值）
//                g.drawString(logoText, positionWidth + num, positionHeight + num);
//                if(num != 0){
//                    g.drawString(logoText, positionWidth - num, positionHeight - num);
//                }
//                num += size*100;
//            }
//
//            // 9、释放资源
//            g.dispose();
//            // 10、生成图片
//            os = new FileOutputStream(targerPath);
//            ImageIO.write(buffImg, "JPG", os);
//
//            System.out.println("图片完成添加水印文字");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (null != is)
//                    is.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            try {
//                if (null != os)
//                    os.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public static int imageFont(int font,BufferedImage buffImg){
//        int fontSize = (int)Math.round(buffImg.getWidth()/buffImg.getHeight());
//        if(fontSize == 0){
//            font = 25;
//        }
//        if(fontSize == 1){
//            font = 20;
//        }
//        if(fontSize == 2){
//            font = 15;
//        }
//        if(fontSize > 3){
//            font = 10;
//        }
//        return font;
//    }
//
//    public static String logoText(){
//        String name = "luqi";
//        String phone = "15692129880";
//        phone = phone.substring(phone.length()-4);
//        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
//        String time = df.format(new Date());
//
//        StringBuffer logoText = new StringBuffer(name);
//        logoText.append("-").append(phone).append("-").append(time);
//        return logoText.toString();
//    }
//
//    public static byte[] markImageTextByBytes(byte[] bytes, String logoText, Integer degree) {
//
//        ByteArrayOutputStream os = null;
//        byte[] imageInByte = null;
//        try {
//            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
//            Image srcImg = ImageIO.read(in);
//
//            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),
//                    srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);
//
//            // 2、得到画笔对象
//            Graphics2D g = buffImg.createGraphics();
//            // 3、设置对线段的锯齿状边缘处理
//            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
//                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//            g.drawImage(
//                    srcImg.getScaledInstance(srcImg.getWidth(null),
//                            srcImg.getHeight(null), Image.SCALE_SMOOTH), 0, 0,
//                    null);
//            // 4、设置水印旋转
//            if (null != degree) {
//                g.rotate(Math.toRadians(degree),
//                        (double) buffImg.getWidth(),
//                        (double) buffImg.getHeight());
//            }
//            // 5、设置水印文字颜色
//            g.setColor(color);
//            // 6、设置水印文字Font
//            g.setFont(font);
//            // 7、设置水印文字透明度
//            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
//                    alpha));
//
//            // 8、水印内容
//            StringBuffer wechat = new StringBuffer(logoText);
//            for(int n=0; n<=10; n++) {
//                wechat.append("   " + logoText);
//            }
//
//            // 9、drawString 第一参数->设置的内容，后面两个参数->文字在图片上的坐标位置(x,y)
//            int num = 0;
//            for(int i=1; i<10; i++) { // 平铺水印（需要设置对称值）
//                g.drawString(wechat.toString(), positionWidth + num, positionHeight + num);
//                if(num != 0){
//                    g.drawString(wechat.toString(), positionWidth - num, positionHeight - num);
//                }
//                num += 100;
//            }
//
//            // 10、释放资源，转成字节流
//            g.dispose();
//            os = new ByteArrayOutputStream();
//            ImageIO.write(buffImg, "JPG", os);
//            os.flush();
//            imageInByte = os.toByteArray();
//            fileToBytes(imageInByte, "d:/qie_text_rotate.jpg");
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (null != os)
//                    os.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        return imageInByte;
//    }
//
//    public static File getFile(String url) throws Exception {
//        //对本地文件命名
//        String fileName = url.substring(url.lastIndexOf("."),url.length());
//        File file = null;
//
//        URL urlfile;
//        InputStream inStream = null;
//        OutputStream os = null;
//        try {
//            file = File.createTempFile("net_url", fileName);
//            //下载
//            urlfile = new URL(url);
//            inStream = urlfile.openStream();
//            os = new FileOutputStream(file);
//
//            int bytesRead = 0;
//            byte[] buffer = new byte[8192];
//            while ((bytesRead = inStream.read(buffer, 0, 8192)) != -1) {
//                os.write(buffer, 0, bytesRead);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (null != os) {
//                    os.close();
//                }
//                if (null != inStream) {
//                    inStream.close();
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        return file;
//    }
//
//    public static byte[] getBytesByFile(String filePath) {
//        try {
//            File file = ImageRemarkUtilTest.getFile(filePath);
//            //获取输入流
//            FileInputStream fis = new FileInputStream(file);
//
//            //新的 byte 数组输出流，缓冲区容量1024byte
//            ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
//            //缓存
//            byte[] b = new byte[1024];
//            int n;
//            while ((n = fis.read(b)) != -1) {
//                bos.write(b, 0, n);
//            }
//            fis.close();
//            //改变为byte[]
//            byte[] data = bos.toByteArray();
//            //
//            bos.close();
//            return data;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * 将Byte数组转换成文件
//     * @param bytes byte数组
//     * @param filePath 文件路径  如 D://test/ 最后“/”结尾
//     */
//    public static void fileToBytes(byte[] bytes, String filePath) {
//        BufferedOutputStream bos = null;
//        FileOutputStream fos = null;
//        File file = null;
//        try {
//
//            file = new File(filePath);
//            if (!file.getParentFile().exists()){
//                //文件夹不存在 生成
//                file.getParentFile().mkdirs();
//            }
//            fos = new FileOutputStream(file);
//            bos = new BufferedOutputStream(fos);
//            bos.write(bytes);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (bos != null) {
//                try {
//                    bos.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (fos != null) {
//                try {
//                    fos.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    /**
//     *
//     * @param filePath  原图片路径
//     * @param content  添加水印文字
//     */
//    public static boolean photoAddWater(String filePath,String content){
//        //获取原图  图标
//        ImageIcon imageIcon=new ImageIcon(filePath);
//        //创建image对象
//        Image image=imageIcon.getImage();
//
//        int width=image.getWidth(null);
//        int height=image.getHeight(null);
//
//        //创建图片容器
//        BufferedImage bufferedImage=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//        //创建绘画环境
//        Graphics2D g=bufferedImage.createGraphics();
//
//        //文字颜色
//        g.setColor(Color.RED);
//        //字体
//        Font font=new Font("宋体", Font.BOLD, 50);
//        g.setFont(font);
//        g.drawImage(image, 0, 0,null);
//        g.drawString(content, (width/15)*8, (height/15)*14);
//        //更改
//        g.dispose();
//
//        FileOutputStream outputStream=null;
//        try {
////            outputStream=new FileOutputStream("d:/qie_text_rotate.jpg");
////            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(outputStream);
////            JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bufferedImage);
////            设置1 原图保存
////            param.setQuality(1, true);
////            encoder.encode(bufferedImage, param);
//            outputStream.close();
//            return true;
//        } catch (FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
////        } catch (ImageFormatException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }finally{
//            try{
//                if(outputStream !=null){
//                    outputStream.close();
//                }
//            }catch (Exception e) {
//                // TODO: handle exception
//            }
//        }
//        return false;
//    }
//
//    public static void main(String[] args) {
//        String srcImgPath = "d:/2619375849941950412_补充资料图片类型.jpg";
//        //String srcImgPath = "C:/Users/qi.lu/Desktop/8c192ce3288835fb9aceb2f886c1cdc.jpg";
//        //String srcImgPath = "https://test-files.meditrusthealth.com///insurance/20210722/2b0c3340a9594de583526b485124bbeb_compress_0abbca3b300f4de2a41f46df63b12942_1617953000378.jpg";
//        //String srcImgPath = "https://static-core.meditrusthealth.com/8768665954864731852_T1_img.png?Expires=1644420979&OSSAccessKeyId=LTAI5tEBSH28c9NZydcckP9M&Signature=94NQdeTsJYsVERC%2BgA4c2%2BCs1qc%3D";
//        StringBuffer wechat = new StringBuffer("卢奇-9880 20220112011259");
//        for(int n=0;n<=10;n++) {
//            wechat.append("  卢奇-9880 20220112011259");
//        }
//        System.out.println(wechat.toString());
//        String logoText = wechat.toString();
//
//        String targerTextPath2 = "d:/qie_text_rotate.jpg";
//
//        System.out.println("给图片添加水印文字开始...");
//
//        //photoAddWater(srcImgPath,logoText);
//
//        // 给图片添加水印文字,水印文字旋转-45
//        markImageByText(logoText, srcImgPath, targerTextPath2, -30);
////        byte[] bytes =  getBytesByFile(srcImgPath);
////        bytes = ImageRemarkUtil.markImageTextByBytes(bytes, "卢奇-9880-20220112011259", -30);
////        String publicFileURL = Base64.getEncoder().encodeToString(bytes);
////        System.out.println(publicFileURL);
//        System.out.println("给图片添加水印文字结束...");
//
//        //System.out.println(logoText());
//
//    }
//}
//
