import {Component, OnInit} from '@angular/core';
import {WeatherService} from './weather.service';
import { getLocaleDateFormat } from '@angular/common';
import { DatePipe } from '@angular/common';




@Component({
  selector: 'app-weather',
  templateUrl: './weather.component.html',
  styleUrls: ['./weather.component.less'],
  providers: [DatePipe]
})

export class WeatherComponent implements OnInit {
  constructor(private weatherService: WeatherService,private datePipe: DatePipe) {
  }
  isSeen=true;
  isDisplayed=true;
  isDisplay=true;
  isDisplaying=true;
  cityName = '';
  zipCode=null;
  currentWeatherInfo = null;
  forecastWeatherInfo = null;
  weatherInfo = null;
  dailyWeatherInfo =null;
  dailyforecastWeatherInfo=null;
  myDate = new Date()
  oldDate=''
  time=null;
  historyWeatherInfo=null;
  historyforecastWeatherInfo=null;

  getWeather(): void {
    this.isSeen=!this.isSeen
    this.weatherService.getCurrentWeather(this.zipCode).subscribe(data => {
      this.currentWeatherInfo = data;
    })
  }
  getForecastWeather():void{
    this.isDisplayed =!this.isDisplayed
    this.weatherService.getForecastWeather(this.cityName).subscribe(data => {
      this.weatherInfo = data;
      console.log(this.weatherInfo)
      this.forecastWeatherInfo = this.weatherInfo.list.slice(0, 10);

    })
  }
  getDailyWeather():void{
    
    this.isDisplay =!this.isDisplay
    this.weatherService.getDailyWeather(this.cityName,this.zipCode).subscribe(data => {
      this.dailyWeatherInfo  = data;
      this.dailyforecastWeatherInfo=this.dailyWeatherInfo.forecast.forecastday

    })
  }
    getHistoryWeather():void{
      this.time= this.myDate.getTime()- ((24*60*60*1000) * 1)
      this.myDate.setTime(this.time)
      this.oldDate = this.datePipe.transform(this.myDate, 'yyyy-MM-dd')


      this.isDisplaying =!this.isDisplaying
      this.weatherService.getHistoryWeather(this.oldDate,this.zipCode).subscribe(data => {
        this.historyWeatherInfo  = data;
        this.historyforecastWeatherInfo=this.historyWeatherInfo.forecast.forecastday

        
      })
  }
  
  clearDetails() {
    this.cityName = '';
    this.zipCode = '';
    this.isSeen=true;
    this.isDisplayed=true;
    this.isDisplay=true;
    this.isDisplaying=true;
  }

  ngOnInit() {
  }

}
