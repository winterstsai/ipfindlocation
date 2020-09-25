# ipfindlocation
ip 反查 地理地址 

## 前置條件
環境 JRE/JDK 10 以上.

## 執行說明
執行 jar ipfindlocation-0.0.1.jar 參數1: 資料庫路徑|參數2: ip csv檔路徑|參數3: ip+location csv檔路徑

例如： java -jar target/ipfindlocation-0.0.1.jar ../IP2LOCATION-LITE-DB11.BIN ../ip.csv ../iplocation20200925_1326.csv

## 參數1: 資料庫路徑 例：IP2LOCATION-LITE-DB11.BIN
下載資料庫 (須註冊)
Free IP2Location BIN Data: https://lite.ip2location.com

## 參數2: ip csv檔路徑 例：ip.csv
### ip.csv 格式(不須標頭)

121.22.24,22,xxxx<BR>
11.1.3.1,xxxx<BR>
222.25.112,xxx<BR>

## 參數3: ip+location csv檔路徑 例：自行命名：iplocation20200925_1326.csv

### iplocation20200925_1326.csv (ip,地理位置)

"121.22.24","IP2LocationRecord:
	IP Address = 121.22.24
	Country Short = CN
	Country Long = China
	Region = Hebei
	City = Qinhuangdao
	ISP = Not_Supported
	Latitude = 39.93167
	Longitude = 119.58833
	Domain = Not_Supported
	ZipCode = 066000
	TimeZone = +08:00
	NetSpeed = Not_Supported
	IDDCode = Not_Supported
	AreaCode = Not_Supported
	WeatherStationCode = Not_Supported
	WeatherStationName = Not_Supported
	MCC = Not_Supported
	MNC = Not_Supported
	MobileBrand = Not_Supported
	Elevation = 0.0
	UsageType = Not_Supported
"<BR>
"11.1.3.1","IP2LocationRecord:
	IP Address = 11.1.3.1
	Country Short = US
	Country Long = United States of America
	Region = Ohio
	City = Columbus
	ISP = Not_Supported
	Latitude = 39.96638
	Longitude = -83.01277
	Domain = Not_Supported
	ZipCode = 43218
	TimeZone = -04:00
	NetSpeed = Not_Supported
	IDDCode = Not_Supported
	AreaCode = Not_Supported
	WeatherStationCode = Not_Supported
	WeatherStationName = Not_Supported
	MCC = Not_Supported
	MNC = Not_Supported
	MobileBrand = Not_Supported
	Elevation = 0.0
	UsageType = Not_Supported
"<BR>
"222.25.112","IP2LocationRecord:
	IP Address = 222.25.112
	Country Short = CN
	Country Long = China
	Region = Shaanxi
	City = Xi'an
	ISP = Not_Supported
	Latitude = 34.25833
	Longitude = 108.92861
	Domain = Not_Supported
	ZipCode = 710032
	TimeZone = +08:00
	NetSpeed = Not_Supported
	IDDCode = Not_Supported
	AreaCode = Not_Supported
	WeatherStationCode = Not_Supported
	WeatherStationName = Not_Supported
	MCC = Not_Supported
	MNC = Not_Supported
	MobileBrand = Not_Supported
	Elevation = 0.0
	UsageType = Not_Supported
"<BR>

### 參考網址

https://github.com/ip2location/ip2location-java

