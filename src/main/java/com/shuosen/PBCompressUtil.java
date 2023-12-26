package com.shuosen;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;


/**
 *  PM体育压缩工具对接工具
 * PB 压缩工具
 * @author  christ
 */
public class PBCompressUtil {

    private static final int BUFFER = 1024;

    public static String compress(String str) {
        try {
            if (null == str || str.length() <= 0) {
                return str;
            }
            // 创建一个新的输出流
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            // 使用默认缓冲区大小创建新的输出流
            GZIPOutputStream gzip = new GZIPOutputStream(out);
            // 将字节写入此输出流
            gzip.write(str.getBytes(StandardCharsets.UTF_8)); // 因为后台默认字符集有可能是GBK字符集，所以此处需指定一个字符集
            gzip.close();
            // 使用指定的 charsetName，通过解码字节将缓冲区内容转换为字符串
            return out.toString("ISO-8859-1");
        } catch (Exception e) {
            return null;
        }
    }
    public static String unCompress(String str) {
        try {
            if (null == str || str.length() <= 0) {
                return str;
            }
            // 创建一个新的输出流
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            // 创建一个 ByteArrayInputStream，使用 buf 作为其缓冲 区数组
            ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes(StandardCharsets.ISO_8859_1));
            // 使用默认缓冲区大小创建新的输入流
            GZIPInputStream gzip = new GZIPInputStream(in);
            byte[] buffer = new byte[256];
            int n = 0;
            // 将未压缩数据读入字节数组
            while ((n = gzip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
            // 使用指定的 charsetName，通过解码字节将缓冲区内容转换为字符串
            return out.toString("UTF-8");
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 数据解压缩
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] decompress(byte[] data) throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // 解压缩

        decompress(bais, baos);

        data = baos.toByteArray();

        baos.flush();
        baos.close();

        bais.close();

        return data;
    }
    /**
     * 数据解压缩
     *
     * @param is
     * @param os
     * @throws Exception
     */
    public static void decompress(InputStream is, OutputStream os)
            throws Exception {

        GZIPInputStream gis = new GZIPInputStream(is);

        int count;
        byte data[] = new byte[BUFFER];
        while ((count = gis.read(data, 0, BUFFER)) != -1) {
            os.write(data, 0, count);
        }

        gis.close();
    }


    public static void main(String[] args) {
        String s = "H4sIAAAAAAAAAO1da5Mb5bH+Kyp9Zc1575f9ZiAOB1hMYYdTFEW5xtqxV7FWciStUzabquRQBC8G\n" +
                "AsFgwAlggiEOPmCONyUwtqna37LaXf+LdM/oMjdpRiNpNiZrKHtmNDPvdZ6nu99+u196pbxaqS6X\n" +
                "F8vlhXK7/usKHB1eazdKx916vdoqHW+sNevOqltvw++VVt2B33fvvbv7zqt4Pz5ohFGcLZRXW+3y\n" +
                "Ilkot5p4lSpGKFNWcioV/Fg5Ddfw3/Iig5tWT5UXTzm1lguHp9t4O2fcCCGJ8W53vHcQQwS+ueK/\n" +
                "uV1zz/lvWamttf0qrza8n1bPej9UWvicxMrAOcUfaq5/g1NbKy++VC6/vFCurTVrvadX6nBwqll1\n" +
                "68sUzk+67cOrjbU6vpw8Sgi2eRleVT72PByeanr3v1Q+gm9pnHWbTrvaqA+76FijiW3B4lZafrE1\n" +
                "t1/RFa9JzFCL1/GEaWYJx/a4rZbfMGwHCbz7yYb30uMN//oqtutQrwf6zWm5/fHDRvaLc7Bl/UF0\n" +
                "W21CmN8Ex2vCcXxy1endvXK2dXgZXvISXqyf9sqCa3DhlUhVV856zZAcDxt1r9YrrZXGb+FFL7ot\n" +
                "711Y8u6lm3v//Nw/PRk+9wdzpYWP0AWxIPHq6qp/teaV6feV0JIqy6FcxjjXXONT/m2rba/khn97\n" +
                "w79daiGIlIIwKaRgMHMafrc22vC7MNix2GAc6QbOJGgTwYt1vxcaXk3DY95o4+FxWv7dwqAcmNiW\n" +
                "cwYFKiKpjpVDe+WwXjlcy6zlsPLvXob/FkZ1u+h3O0vu9r1vbuxe/XDY7YHzdu8hv9vZAseuX1D9\n" +
                "zqfRzmfwB/tTG6aU0nzY+dCk8iH6qD9u/kCs1BPGA97BuWASP2zKKI32E4uOBw5Hv596Bfh91TtJ\n" +
                "GRfNCVFSGAn11lSSaHk8Mi7UimF5jwTLe2RUeWnjI/vjw0eOT/e734fGp38+6fgIqqF/tSXScsU5\n" +
                "iYwPTx8fywAUGIHJyanw3hDuLxEdHxUcHx4cH55lfIgxXBsYJ/gUiWYiWp6MjA9jge/mkWB5j4wq\n" +
                "rzc+CGPnelDZHlJNe8VH0VNAGuXt7zfg09i+8+beq38pIWKeanvD1vbG6fV/dN/4e/fND3yu2/vD\n" +
                "5b1/XsWbWhVESW8CzJs2dY82+XjaFEwxrQk3M6LNf3vSBELg8uEgzV5VCyVNJg2zFr41BtNCe+A0\n" +
                "ljQJ4AgQmZGAXhwBbD6kKS3gFFM4sTlhhM+TNCPdXiBpWqA5STljQC3QqVFQzkCaSisGVSaSEcYJ\n" +
                "jYHkrElTWOBpZSSypiQyVt48SDMyPgWSJgWis4ZQ+ENgGuqFiUmTUSYZjBGMDUf+nDdpCkU9UkCo\n" +
                "1yQu1DzkpPnfx4+UfnH6/Nl26QgIBKUlt146Vq2frnnDn0ic8IERwUWUOUEkN4bYiK6JQucIXZMQ\n" +
                "f2B80rRGU97jTD7gTDmKNElU14TzVRcbVGksuyfOVRs1j4rK2ch079s73WufbHW6b3zW/f7VB6+/\n" +
                "2f301e6ty1udBzduwtn2nY+z8+yzY3mWj6NZpqUd6qagesV0U5qTZ1vNxZbrtBr1RaNB9Eui3d3v\n" +
                "bncvvt69eMNrNbR+96t3g+S7NCTflcaqGyTgV/pQolj/g8fZ3ONaNqDXmzc5zN4Bq4YbOmDTKAKd\n" +
                "THjUQx4eJmSc3/168IR68Eg9BjCWUI8kAeFkwqOjETBYG5FQGxGpzc7vf4RX7rx/a1ghaqUQ2SoU\n" +
                "fNqrk0ys0xjzQq/xRUhKYQUuXHABssK44oukQiAWgXofcLwgUf2RDZmQjGBCogUKOiBAAp3zuKQS\n" +
                "ZUIGesmQmViQmVgm9V4zJohQQLwSqq7SmJDqIPMGyzs0qrxZMeFYVvPpsHyMrtNF7JtjAg4QzY8x\n" +
                "vs4WlXck1gWB+eAdy3VOFoV/hwoc6/XBocHbD3uHdngHJ8O3cBq4zuBJ/8iuq0XhVQKqI3u/C4bP\n" +
                "+Yd88GYRqJOQg7KFClzWw0KEWT/cP7R4C/eOJemXLSW0lnlHalCIHDZKmuGLpR0eq8EbYOINDilf\n" +
                "52JR+8diXalH9SLjj3odDHLaoIsJhVK5f8jW+WL/BorHvDxaSjnmNk9WndIRmVlKoUQlSClacmXC\n" +
                "Uspog3hYSKGEaG2imj3JbBDPJox0v38fZI4H994BsQNEklt3gYh3Lm3sfPBtdiHkufHKvkaiHimH\n" +
                "gGCNBNBHSEbNrGzkITFEmSQxZPvuG91vNrp//wu0uvvH17Y6e5s/dO93oEci8siLycaA7LIITZJF\n" +
                "/MamySJ0lrIITZBFMlI/nb0sQqeSRaJP55NFeoNQvCwSLrhwWYQJUaxaPiuuHYnNaarnrEBdcm2z\n" +
                "groVEc1TDay1NA+m9xRPgKLKyolW22mvtTKqnc8552qNc9XKVueZtTNOQejODMWlufB3HUZ3Mzd0\n" +
                "P3qusuI03fqZxlbnaM0903Lqy80gqh8dorrzW+f87JA9CGAHyL5PyN4vq3BkjxR8gOwzQnbUoshA\n" +
                "ecKjQrDe5MZ6ovcJ67vXPul+eqd7/fXsMH9sCphHeyoNfeOxJbv5ofxTPqm9UD3TboTQ/al5ors8\n" +
                "QPf9R/eiVltj6F7seuMBus/TPNNfRGI+uqsR6G7RjyVonqEI+bnhnQzg/VTTdU9UGqs9/5Es8L6z\n" +
                "8eGDGzd3Pvh2q/Pgw5+6F690X7u0c+3dvfv3u7cuZ4f841NJ9pwJEzJlkOLsNtABu9/dhg7YubSx\n" +
                "1dm9/gFe+HETLgDtlafyfPQbUqgTBzSSwvxk6MwIRwHnu0QnDs2V1oYJTgmRnMVM41EnDsZMLicO\n" +
                "eL2iEoZASEMJjS1GR504qFL5PR/D3V6gE4eATreKC4BSKpUMeNDgykgGHw7DiRCcU44erJrFfGpi\n" +
                "PhxSTupTER4WpQSBaSLh1ZTSWHlRHw4mJvYZSRienynpjKGYs061XjrCWHaKYYkUwwWKU3lXADxU\n" +
                "zbkCkNUlfqnaWjlTrW91nq42q7VadgJZSiMQNY5AYFLpheAM4/MgEJFIIN3O5u69K9v33il1//r2\n" +
                "zu3PB1x66/Le5l+BTYIk8tjkJOI1Zj9syqGCD2TTzDAx6msvCiYkcGzehULgd2bzGxroVLLokgtN\n" +
                "cEqHT69Va05zq/OEU6+6hWEItHwohFLLZuYrnAFDltzWb9aw7Y+5zdNbnaOtihOyPyxNChu9+u+H\n" +
                "SssKdqE9gI2ZwYa3VSCvf4Gev3Sxu/EDaGnb926gdub5F3S/+Hqrs7Pxdve9i7PVV8dBBaU+OAyn\n" +
                "nC4OKp6v1iuNegAcByDxfB7ZQu+XbKEPQOIhBAlUQVhe2UL5n9WcQeLxxooL38dhp+m5UWcEhMen\n" +
                "AQSEvzAgmOIAAf2Mbl3uvnWle7/j6R7du1d2Nq8+uOqf7f30j52LF4M4cSQPTpj9wglzgBMPIU6g\n" +
                "MJEVJyJrnSiJSzp/nNi7f79v+d797vbu1Q8mEiCemM5eYUlwkZPxmCvL/PDisbX2OdCvWludXyw7\n" +
                "ramME72a74+WceAW8TACAwgQOquWEQMGxrCv565l/LgJysVEaPA/00kPxnNlC82totDgxbV6FaDg\n" +
                "iFNZqy83yul+yuPFhH2xOUQKPkCDhwkNMu9piKIBFdZO4RM1nanyWGXFcZtbneedU6ecSWyUqb5R\n" +
                "Y+UGakl4nUMVqGf8+aPuN1e2Ott339j57O7undt713/qXr9ensZM2WvC/kCGOoCMhxEypKB9zWJC\n" +
                "PxtrlIzFN8mMGEnSw0Kyy+T1692LN0ANB+377a93Lm10r132vUt+3Nz52ze7Gz9kx4vDU65pGBXC\n" +
                "C1IcXuxsXt2+d6P72g/QCwAd1y53//RF935nq9N96xr85m8LG0DHM3mggxQEHX0HDwzcILgBKdRI\n" +
                "qYnnZ1j2YgWMiJFijUbnGiW9gFixmA8k4s9hLc3lXqMUYIgkQsKkx9BZqe41RGQtJ9F/gxSKnP3e\n" +
                "NxyDjSiY0kJSRSZ3r6EwfFZZwoSnges09xo+eciScNwqQo2lXAhqMIJIzLsqFiKFiv9k95p9ZxYQ\n" +
                "RnN7cArN8tu2MyqmAJy7732Jf393e6uz88kVwNfsTPL0lBZuGZY8aXFM8nxjuVk9veZe2Oo802i6\n" +
                "9QuN0lMN5zdr1Xp52uWvwK7JYoJsoZ8lxrDDrcqSpEWmVIxSzS3VglDKTQyxogQScgicgEAMEUoA\n" +
                "cGBALygyRlRRAoHKT0UggW4vNDIlzGLFhQU1Cjo0GsQpA4MwAvKnQiYBPOc0FowsyiChoEp5gmwJ\n" +
                "yaxgXAqttef8l8IgagaRKQ8YZBrjZk7dxJPV8xs3MzLIklO/4NQBpEvPuc32WsvZ6hyuub/GzayN\n" +
                "7EwyrZ+VCVs7WXFMcsQLPrnVeco5X3rifM2pl550mk552sVRVjCLAPwQJWAeYeBFrtJYRHMNUi83\n" +
                "kkDHWJPq5Q+TOxeLIEkxGGAvlDJM8TQWEWSK+Mbhbi+QRbgFFQvULSEl5RbNl5OyiMbYuUpYQHRm\n" +
                "LU9lEdAfp2IRDqqnpkQD7lBD4uwec/Nn/IBF9lcP6S+R8bEsAmTPYktkYu56iB9mb+/P/wtKyEff\n" +
                "7N750o/ys9XZvvNx9+0/4g6ySWL+TEUnBFiTh+ikwKX0Z+C9oJQ823BWytPasopaRx/AGFcgwUor\n" +
                "lQIU0ySFQxCBMIAmwyBLFgX4FA4h+XaKGYAjpqE+XKHBh6RxiAkGa8vBIcV6EQxMWbgbh1BlKeec\n" +
                "iEDnZ+YQDOANWiS8wChp0reKTamJcBgMTgnDIMOgk6RuFePBrWIHHJKVQ351pglPuhNtR/aWQ+LB\n" +
                "JgSJ7BUbpYoYGVdFdJ9EcqysThVt4q2Pu5/eAV7ZvLp998vdzc+z08hjaTQyJoQtRp0YOnBSI9Vc\n" +
                "YgtJlcQiDz78CddD/v8jINAf3oR/+37t6IXy6R0/iO+AXA5PFIpioDEAqOJ3CwBuKFFp+4JBfPfi\n" +
                "tVvJpKaxjAsYsiqMLrS/cHGyD8MDOK5lw33LjWDacCK18KLPh0u0EXzRlExeoo8wA3ZNDc7hz4OD\n" +
                "4Bz7GpyjNwiF7qKXuBLDUDwyoDiLlK+FES69rCggTnDB0q20JJ9+DWoZyPca5DDQ6KmKlRNf5iP5\n" +
                "haNIvxe5jV5Jxq0kGmnMCDm5cAR8hjiCy5za0HiCmrhwNF0uBBh/q+CbUMwAbpKYcBwTjvTE+/Z/\n" +
                "RsLRaBln8ugtM5OXNNHRjS1mEnmpb7plU8hLJ51a7US1fuJszeP1LPLSC27TXXWrlZW1M96WsPO1\n" +
                "Cey4L0whMXmNDthxacImODs3kemxRn0ZdwgvnW82WjXnXDnZiz1DvP9ByjjGOe7rUwSDqOiIgERi\n" +
                "AhJma8FkbsRQS2Mp4+ICErFTCkgcfd5h+ilQxJj/rvECEpmtgMSSog77o54mILFcAtJQdNUKQ9oA\n" +
                "pBvpLx36DY+6fcQGSWn0c8F0WdaAYNkbJNbrMklivOzBMolgcQSks40WKslaUBQeuBTRgDeSRKla\n" +
                "hUo+FCj5UHrJkVHjkVGL5YsIjNoY6Y3NXpxkyeLkBBWaPkYzLW7P69Daw4RGGdEyrUx/0Z+OmrYW\n" +
                "dDTJLYNJBCJlPFhSXJzM5zVGQDKyaNnRVlIlUzNrUTGFOBnp90KjMnGQZQV2Jyb45GH8OBQGkGRx\n" +
                "UqL/nkbfUylJTDeNi5OETyVOKpgrhFkpMTyXTA/LJIPjklecDI9PkfkoASrRL4YqTJJFWGR8MiQU\n" +
                "8ZJqMXhWMsUEV2GcjycUESzYYdkyfISNFajqoe1Vwleq47nvohlFVFC/yJbBZJYZRbII3MNMIr28\n" +
                "HkwM83owGcgqogLX9SDfBzPDpCKBrB1+JpHeMQ0cs8HtnA/yfXARyC8ie/lFAm8QgTcIFsg7Eko2\n" +
                "Mrwsh5lE1PBQDw9NIO2Il/+ED/KDDE8ovJKQReY330/4UR4m/xBFqSc5zbnWKJXf6z3zrrknG816\n" +
                "td2qrLigiTxbrUyghzw5nR5CiQmJEnPZez/CdHvj5t79+/7yp+fpvvPZXfi/e/FGeaptMbS4DffD\n" +
                "nNlKYb5sxjWIrJSEhZS4L7XFZEoSwx0qaVSG9J8ys/AQNnoBC0rGAdy5VcqmuiZO5dtOi443MOh9\n" +
                "oHxjBfrzEGnt5L7tHBOXMyOMBfKGv9NkFMEnlhnCwyKkUEIpLw+oJakyyn+2b3saAxezIEhJmEG8\n" +
                "CA2Jzok06lbCGetzyDQWrraXIvlEq9JouicqK079NNpnMm3Kfu/L7sXXdzav7m4AzMLZ9t0vt7//\n" +
                "Q3aSSd2iPT7DJVBwcF1Ix9zf52fsQn+aSxvdL77efWdz96t3PZLBLWa3LkciFD+dy/KliEI/c0ER\n" +
                "e7iMOBPGtFMmJfcRWaDzYVRrjFm+cMFiOssXswod5piSVEsWU4dili9QJOZu+epNgflavtDBC3qY\n" +
                "SEMVsVKNJIW45YtzjoGWOXqPx7oswfJl5YwMXxi9mFHmVZ4Rm2r4ooYWZvgKDtqcDF9D7yqGrmmY\n" +
                "5lIwo1l46OyYoUMWh48LetFQzhWLdmCfWI+ec5v9Tyxk6u1+8VXJDrsxeJpxDCXTMO08OEBPEBqt\n" +
                "Ql+W+FV9eVAH3KISqMOtP4XqEDjNPJoZDIcTDOgs1qG9soqUyS36bRNJLQi1isqIj1586lDfCsKZ\n" +
                "RJfFVD9vwfLtFrIwOzQgkiWaC67T16FZZgNl0jJ0sNuLFMmlkQqT22t0tGdj0Tc5z60gXsp3Q9GH\n" +
                "NR5cP75ZaGK36zBPMgLFEFzE8ES4VJFc2qlF8sjwFGk2ZIZr6qX05cSaiMYk04cHAxSCAAMkpSR6\n" +
                "DES7K5aGOKjBPCKDwyMzeVACg0NVuWaYBsHGrMixNMQhr/9geYdGlVe00ZAM8xB7+XijeYh533qY\n" +
                "Ow3xwDJI1xOTEPezDRN8Kmi7GyT3pXLRlntmPALkVR7k9fWrTPDNvUPveJw9L+LX+URj7WSqi79O\n" +
                "Mucpzysl6OIvknWxBBd/wJP8uXyTrHnJUSxCMalLR0v/hXvG3GrTKS2VjmTXu5bgJDXv4/gNY5SG\n" +
                "th6r6TaMLYxWv0iS+vW4U1t2W6WnoAN+2ahfcGruhdIRF1oA0s+F0lJQAXscTn6Zw/lfFb2BTEpD\n" +
                "pJWUIwRSFhEs4s7/6I6F/t8AlsaS2DbUmDrBbS7BAuoFdTIWPxzg3RgkRwULTjPbFJOMScFuL9L5\n" +
                "n8HXj8u7uBWcCR5mriz+bSgUSsyqAxIG1/Ft4bEFSasmZfqIc66woNdpoFwYBJIuWRgzqSTzMzL2\n" +
                "jSSJrEmFRUZTn9DGC5QaYhdcRsXvT2nNTYRiRnEMel/DDBEDjkEfKDJTjkky6V3udN96nxCbnUye\n" +
                "HU8kyktyPzJsO4CdHRKJMTNLD59EG/3WiSBBPDsxOfi1LDiHGHpLeFuGLcrIKeQg0BuZA0CghxZX\n" +
                "MTeSODnkdFfB/U6cwlRlaJxI1TpxRucmh3C3F0gOliEfewtRICLmIQfoHAw2BB8waIIqTtYxcjAT\n" +
                "q4GRGBWCWQqKskAhgabHqLAz2F0cHp8C1U4tQIsz1gL5gkCEuXImHR/0LtUGnrcoh8WdxaN6Jw3O\n" +
                "4xzjA3IO5qODXqeJ3ktRvTMUs2Si8ZkFeyJs/t87AbJ8+V/Sd51OapwAAA==";

        System.out.println(s);
        try {
            System.out.println("解密后："+ decompress(s.getBytes()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
