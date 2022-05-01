package patientDataUtils;
import java.util.ArrayList;
import java.util.Collections;

public class FFT {

    // complex number class with methods for operations
    static class Complex {

        private double real, img;

        // defaults as complex number 0
        public Complex() {
            real=0;
            img=0;
        }

        // creates a complex number
        public Complex(double r, double i) {
            real = r;
            img = i;
        }

        // returns real part of the complex num
        public double getReal() {
            return real; 
        }

        // returns imaginary part of the complex num
        public double getImaginary() {
            return img;
        }

        // add complex number to another
        public Complex add(Complex z) {
            Complex temp = new Complex();
            temp.real = this.real + z.real;
            temp.img = this.img + z.img;
            return temp;
        }

        // subtracts num from complex num
        public Complex subtract(Complex z) {
            Complex temp = new Complex();
            temp.real = this.real - z.real;
            temp.img = this.img - z.img;
            return temp;
        }

        // multiplies complex number by another
        public Complex multiply(Complex z) {
            Complex temp = new Complex();
            temp.real = this.real * z.real - this.img * z.img;
            temp.img = this.real * z.img + this.img * z.real;
            return temp;
        }

        // multiplies complex num by a scaler
        public Complex multiply(double n) {
            Complex temp = new Complex();
            temp.real = this.real * n;
            temp.img = this.img * n;
            return temp;
        }

        // return conjugate of complex num
        public Complex conjugate() {
            Complex temp = new Complex();
            temp.real = this.real;
            temp.img = -this.img;
            return temp;
        }

        // return magnitude of complex num
        public double abs() {
            return Math.hypot(this.real, this.img);
        }

        // divides complex num by another
        public Complex divide(Complex z) {
            Complex temp = new Complex();
            double d = z.abs() * z.abs();
            d = (double)Math.round(d * 1000000000d) / 1000000000d;
            temp.real = (this.real * z.real + this.img * z.img) / (d);
            temp.img = (this.img * z.real - this.real * z.img) / (d);
            return temp;
        }

        // divides complex num by a scaler
        public Complex divide(double n) {
            Complex temp = new Complex();
            temp.real = this.real / n;
            temp.img = this.img / n;
            return temp;
        }
    }

    public ArrayList<Complex> fft(ArrayList<Complex> x, boolean inverse) {
        // pad the signal with zeros fi necessary
        paddingPowerOfTwo(x);
        int N = x.size();
        int log2N = findLog2(N);
        x = fftBitReversal(N, log2N, x);
        int direction = inverse ? -1 : 1;

        // main loop of algorithm
        for (int len = 2; len <= N; len *= 2) {
            double angle = -2 * Math.PI / len * direction;
            Complex wlen = new Complex(Math.cos(angle), Math.sin(angle));
            for (int i = 0; i < N; i += len) {
                Complex w = new Complex(1, 0);
                for (int j = 0; j < len / 2; j++) {
                    Complex u = x.get(i + j);
                    Complex v = w.multiply(x.get(i + j + len / 2));
                    x.set(i + j, u.add(v));
                    x.set(i + j + len / 2, u.subtract(v));
                    w = w.multiply(wlen);
                }
            }
        }
        x = inverseFFT(N, inverse, x);
        return x;
    }

    // Find log2(N)
    public static int findLog2(int N) {
        int log2N = 0;
        while((1 << log2N) < N) {
            log2N++;
        }
        return log2N;
    }

    // swap values of the signal with bit-reversal method
    public static ArrayList<Complex> fftBitReversal(int N, int log2N, ArrayList<Complex> x) {
        int reverse;
        for (int i = 0; i < N; i++) {
            reverse = reverseBits(i, log2N);
            if (i < reverse) {
                Collections.swap(x, i, reverse);
            }
        }
        return x;
    }

    // Divide by N if we want the inverse FFT
    public static ArrayList<Complex> inverseFFT(int N, boolean inverse, ArrayList<Complex> x) {
        if (inverse) {
            for (int i = 0; i < x.size(); i++) {
                Complex z = x.get(i);
                x.set(i, z.divide(N));
            }
        }
        return x;
    }

    private static int reverseBits(int num, int log2N) {
        int reversed = 0;
        for (int i = 0; i < log2N; i++) {
            if ((num & (1 << i)) != 0) {
                reversed |= 1 << (log2N - 1 - i);
            }
        }
        return reversed;
    }

    private static void paddingPowerOfTwo(ArrayList<Complex> x) {
        int n = 1;
        int oldSize = x.size();
        while (n < oldSize) {
            n *= 2;
        }
        for (int i = 0; i < n - oldSize; i++) {
            x.add(new Complex());
        }
    }
}
