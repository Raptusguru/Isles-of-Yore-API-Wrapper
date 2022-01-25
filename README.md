# (JAVA) API Wrapper for *Isles of Yore* (Servers)
***This API wrapper is currently under development and may be subject to significant changes. The API wrapper is not yet in a final version.***
###### Isles of Yore - Steam Page
[![Isles of Yore on Steam](https://cdn.akamai.steamstatic.com/steam/apps/1360850/header.jpg)](https://store.steampowered.com/app/1360850/Isles_of_Yore/)  
[Isles of Yore on Steam](https://store.steampowered.com/app/1360850/Isles_of_Yore/)


## Usage
### Creating the API Object
There are two ways to create the API object. The simple variant offers no possibility to access the configuration.
With the second variant, changes can be made to the configuration before the object will be created.

###### With standard configuration
```java
APIController api = new IslesOfYoreAPI("127.0.0.1", "8085", "Waldi", "NotYourBirthday").build();
```

###### With configuration ability
```java
IslesOfYoreAPI api = new IslesOfYoreAPI();
...
```

### API Configuration
Settings can be made to the API by calling the Config method and setting the relevant values.

```java
...
api.config().setRatelimit(5);
api.config().setPrint_serverResponse(true);
api.config().setHost("127.0.0.1");
api.config().setPort("8085");
api.config().setUsername("Waldi");
api.config().setPassword("NotYourBirthday");
...
```

## Dependencies
To use this wrapper you need these dependencies.

• **FasterXML/jackson-core**  
→ Version: *1.13.1*  
→ [Github](https://github.com/FasterXML/jackson-core)  
→ [mvnrepository](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core)

• **FasterXML/jackson-databind**  
→ Version: *1.13.1*  
→ [Github](https://github.com/FasterXML/jackson-databind)  
→ [mvnrepository](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind)

• **FasterXML/jackson-annotations**  
→ Version: *1.13.1*  
→ [Github](https://github.com/FasterXML/jackson-annotations)  
→ [mvnrepository](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-annotations)

## Leave me a coke
If you like my work and want to support me, you can do so easily via Paypal.
Thank you <3

[![Paypal](https://www.paypalobjects.com/webstatic/de_DE/i/de-pp-logo-100px.png)](https://paypal.me/Raptusguru)

## LICENSE
```
Copyright 2022 Patrick Jack Schreyl (Raptusguru)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
