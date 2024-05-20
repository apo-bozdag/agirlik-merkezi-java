# Ağırlık Merkezi Hesaplama ve Görselleştirme

Bu proje, belirli yüklerin kütle ve koordinatlarına göre ağırlık merkezini hesaplayan ve bunu grafiksel olarak görselleştiren bir Java uygulamasıdır. Uygulama, kullanıcıdan yüklerin kütle ve koordinat bilgilerini alarak bu bilgileri kullanır ve ağırlık merkezi konumunu hesaplar. Hesaplanan ağırlık merkezi ve yüklerin konumları, JFreeChart kütüphanesi kullanılarak bir grafik üzerinde gösterilir.

## İçindekiler
- [Kurulum](#kurulum)
- [Kullanım](#kullanım)
- [Hesaplama Mantığı](#hesaplama-mantığı)
- [XYZ Koordinatları ve Kütle](#xyz-koordinatları-ve-kütle)
- [Görselleştirme](#görselleştirme)
- [Örnek Çıktı](#örnek-çıktı)

## Kurulum

Bu projeyi çalıştırmak için Java ve JFreeChart kütüphanesine ihtiyacınız olacak. Aşağıdaki adımları izleyerek projeyi kurabilirsiniz:

1. Java'nın yüklü olduğundan emin olun.
2. JFreeChart kütüphanesini indirin ve projenize ekleyin.
3. Bu proje dosyalarını yerel bilgisayarınıza indirin veya kopyalayın.

## Kullanım

Projeyi çalıştırmak için aşağıdaki adımları izleyin:

1. `BkmOdev.java` dosyasını açın ve çalıştırın.
2. Program sizden sırayla römorkun kısa kenar uzunluğu, uzun kenar uzunluğu, yüksekliği ve yük sayısını girmenizi isteyecek.
3. Her bir yük için kütle ve XYZ koordinatlarını girin.
4. Program ağırlık merkezi koordinatlarını hesaplayacak ve bir grafik üzerinde gösterecek.

## Hesaplama Mantığı

Ağırlık merkezi, bir cismin kütlesinin dengede olduğu noktadır. Bu proje, ağırlık merkezini aşağıdaki formüle göre hesaplar:

\[ \text{Ağırlık Merkezi (X, Y, Z)} = \left( \frac{\sum (m_i \cdot x_i)}{\sum m_i}, \frac{\sum (m_i \cdot y_i)}{\sum m_i}, \frac{\sum (m_i \cdot z_i)}{\sum m_i} \right) \]

Burada:
- \( m_i \) : i'nci yükün kütlesi
- \( x_i \) : i'nci yükün X koordinatı
- \( y_i \) : i'nci yükün Y koordinatı
- \( z_i \) : i'nci yükün Z koordinatı
- \( \sum \) : Tüm yükler için toplam

Toplam kütle sıfır olamaz. Toplam kütlenin sıfır olması durumunda, program bir hata fırlatır.

## XYZ Koordinatları ve Kütle

- **X Koordinatı**: Yükün römork üzerindeki yatay konumunu belirtir.
- **Y Koordinatı**: Yükün römork üzerindeki dikey konumunu belirtir.
- **Z Koordinatı**: Yükün römork üzerindeki yüksekliğini belirtir.
- **Kütle**: Yükün ağırlığını belirtir ve kilogram (kg) cinsindendir.

## Görselleştirme

Proje, JFreeChart kütüphanesini kullanarak kütlelerin konumlarını ve ağırlık merkezini bir grafik üzerinde gösterir. Grafikte:
- Kütle konumları mavi dairelerle gösterilir.
- Ağırlık merkezi kırmızı bir kare ile gösterilir.

## Örnek Çıktı

Aşağıda, programın çıktısının nasıl görüneceğine dair bir örnek verilmiştir:

```shell
Römorkun kısa kenar uzunluğunu girin (m): 5
Römorkun uzun kenar uzunluğunu girin (m): 10
Römorkun yüksekliğini girin (m): 3
Yük sayısını girin: 2
Yük 1 kütlesini girin (kg): 100
Yük 1 x koordinatını girin (m): 2
Yük 1 y koordinatını girin (m): 3
Yük 1 z koordinatını girin (m): 1
Yük 2 kütlesini girin (kg): 200
Yük 2 x koordinatını girin (m): 6
Yük 2 y koordinatını girin (m): 7
Yük 2 z koordinatını girin (m): 2
Ağırlık merkezi koordinatları:
X: 4.666666666666667 metre
Y: 5.666666666666667 metre
Z: 1.6666666666666667 metre
```
