package com.manggoormy.manggoormy.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.*;
import java.awt.Color;
import java.awt.image.BufferedImage;

@Component
@RequiredArgsConstructor
public class MeasureStoneUtil {

    public static int[][] mask = {
            { -1, -4, -6, -4, -1 },
            { -2, -8, -12, -8, -2 },
            { 0, 0, 0, 0, 0 },
            { 2, 8, 12, 8, 2 },
            { 1, 4, 6, 4, 1 }
    };

//    public static void main(String[] args) throws java.lang.Exception {
//        BufferedImage img = ImageIO.read(new File("img/black_4.jpeg"));
//
//        float texture = detectTexture(img);
//        System.out.println("texture=" + texture);
//    }

    public static float detectTexture(BufferedImage img) {
        float[][] nglcm = getNGLCM(img);
        float homogeneity = getHomogeneityValue(nglcm);
        float entropy = getEntropy(nglcm);
        return entropy;
    }

    public static float lawsTextureMask(BufferedImage img) {
        int height = img.getHeight();
        int width = img.getWidth();

        float[][] rgb = new float[height][width];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                rgb[row][col] = img.getRGB(col, row) / 256f;
            }
        }

        float sum = 0.0f;
        for (int row = 2; row < height - 2; row++) {
            for (int col = 2; col < width - 2; col++) {
                float value = 0.0f;
                for (int dr = -2; dr <= 2; dr++) {
                    for (int dc = -2; dc <= 2; dc++) {
                        value += mask[2 + dr][2 + dc] * rgb[row + dr][col + dc];
                    }
                }
                sum += value;
            }
        }

        return sum;
    }

    public static float getEntropy(float[][] nglcm) {
        float ret = 0.0f;

        for (int i = 0; i < 255; i++) {
            for (int j = 0; j < 255; j++) {
                float value = nglcm[i][j];
                ret -= value / Math.log(value);
            }
        }

        return ret;
    }

    public static float getHomogeneityValue(float[][] nglcm) {
        float ret = 0.0f;

        for (int i = 0; i < 255; i++) {
            for (int j = 0; j < 255; j++) {
                ret += nglcm[i][j] / (1 + Math.abs(i - j));
            }
        }

        return ret;
    }

    /**
     * RGB Space 이미지로부터 Normalized-GLCM 행렬을 뽑아내는 함수
     * 
     * @param img RGB Space 이미지
     * @return Normalized-GLCM 행렬, 256*256 size
     */
    public static float[][] getNGLCM(BufferedImage img) {
        int height = img.getHeight();
        int width = img.getWidth();

        int[][] glcm = new int[256][256];
        init2DArr(glcm, 256, 256, 0);

        for (int row = 0; row < height - 1; row++) {
            for (int col = 0; col < width - 1; col++) {
                Color colorA = new Color(img.getRGB(col, row));
                int redA = colorA.getRed();
                int greenA = colorA.getGreen();
                int blueA = colorA.getBlue();
                int grayA = (int) Math.sqrt(redA * redA + greenA * greenA + blueA * blueA);
                if (grayA > 255) {
                    grayA = 255;
                }

                Color colorB = new Color(img.getRGB(col + 1, row + 1));
                int redB = colorB.getRed();
                int greenB = colorB.getGreen();
                int blueB = colorB.getBlue();
                int grayB = (int) Math.sqrt(redB * redB + greenB * greenB + blueB * blueB);
                if (grayB > 255) {
                    grayB = 255;
                }

                glcm[grayA][grayB] += 1;
            }
        }

        float[][] nglcm = normalize2DArr(glcm, 256, 256);

        return nglcm;
    }

    public static int[][] init2DArr(int[][] arr, int rowMax, int colMax, int initValue) {
        for (int row = 0; row < rowMax; row++) {
            for (int col = 0; col < colMax; col++) {
                arr[row][col] = initValue;
            }
        }
        return arr;
    }

    public static float[][] normalize2DArr(int[][] arr, int rowMax, int colMax) {
        float sum = 0;
        for (int row = 0; row < rowMax; row++) {
            for (int col = 0; col < colMax; col++) {
                sum += arr[row][col];
            }
        }

        float[][] ret = new float[rowMax][colMax];
        for (int row = 0; row < rowMax; row++) {
            for (int col = 0; col < colMax; col++) {
                ret[row][col] = ((float) arr[row][col]) / sum;
            }
        }

        return ret;
    }
}