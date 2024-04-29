#include <netinet/in.h>
#include <sys/socket.h>
#include <netdb.h>
#include <stdio.h>
#include <string.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/fcntl.h>
#include <stdlib.h>

int main(int argc, const char * argv[]) {

    char http_header [2048];
 
    char buffer[1024];
    struct sockaddr_in server_address, client_address;
    int request_sd, sd;
    socklen_t client_lenght;
    int opt = 1;
    request_sd = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
    
    memset(&server_address, 0, sizeof(server_address));
    server_address.sin_family = AF_INET;
    server_address.sin_addr.s_addr = htonl(INADDR_ANY);
    server_address.sin_port = htons(8080);
    
    setsockopt(request_sd, SOL_SOCKET, SO_REUSEADDR, (char*) &opt, sizeof(opt));
    
    if((bind(request_sd, (struct sockaddr*) &server_address, sizeof(server_address))) < 0)
        perror("Cant bind");
       
    if((listen(request_sd, SOMAXCONN)) < 0)
        perror("Cant listen");
    
    while(1){
        
        http_header[0] = '\0';
        client_lenght = sizeof(struct sockaddr_in);
        
        sd = accept(request_sd, (struct sockaddr*) &client_address, &client_lenght);
        
        if(sd < 0)
            perror("Cant accept");
        
        if(read(sd, buffer, sizeof(buffer)))
            perror("Cant read");
        
        char method_type[1024];
        char client_message[1024];
        int i = 0, k = 0;
        
        while (buffer[i] != ' '){
            method_type[i] = buffer[i];
            i++;
        }
        
        method_type[i] = '\0';
        
        if(strcmp(method_type, "GET") == 0){
            i += 2;
            while (buffer[i] != ' '){
                client_message[k] = buffer[i];
                i++;
                k++;
            }
            client_message[k] = '\0';
            
            if(strcmp(client_message, "index.html") == 0){
                
                FILE *fptr = fopen(client_message, "rb");
 
                if (fptr == NULL) {
                    perror("Error opening file");
                } else {
                    
                    fseek(fptr, 0L, SEEK_END);
                    long int size = ftell(fptr);
                    fseek(fptr, 0L, SEEK_SET);
                    
                    char buffer_html[size];
                    char temp[1024];
                    
                    fread(buffer_html, 1, size, fptr);
                    
                    sprintf(temp, "%ld", size);
                    
                    strcat(http_header, "HTTP/1.1 200 OK\r\n"
                                        "Server: Demo Web Server\r\n"
                                        "Content-Length ");
                    strcat(http_header, temp);
                    strcat(http_header, "\r\nContent-Type: text/html\r\n"
                                        "\r\n");
                    printf("Response: %s\n", http_header);
                    
                    if((write(sd, http_header, strlen(http_header))) < 0)
                        perror("Error sending data html");
                    
                    if((write(sd, buffer_html, strlen(buffer_html))) < 0)
                        perror("Error sending data html");
                    
                    fclose(fptr);
                }
            } else if(strcmp(client_message, "img/quokka.jpg") == 0){
                
                FILE *fptr = fopen(client_message, "rb");
                
                if (fptr == NULL) {
                    perror("Error opening file");
                } else {
                    
                    fseek(fptr, 0L, SEEK_END);
                    long int size = ftell(fptr);
                    fseek(fptr, 0L, SEEK_SET);
                    
                    char buffer_img[size];
                    char temp[1024];
                    
                    fread(buffer_img, 1, size, fptr);
                    
                    sprintf(temp, "%ld", size);
                    
                    strcat(http_header, "HTTP/1.1 200 OK\r\n"
                                        "Server: Demo Web Server\r\n"
                                        "Content-Length ");
                    strcat(http_header, temp);
                    strcat(http_header, "\r\nContent-Type: image/jpeg\r\n"
                                        "\r\n");
                    printf("Response: %s\n", http_header);
                    
                    if((write(sd, http_header, strlen(http_header))) < 0)
                        perror("Error sending data header");
                    
                    if((write(sd, buffer_img, sizeof(buffer_img))) < 0)
                        perror("Error sending data img");
                    
                    fclose(fptr);
                }
            }
        }
        
        http_header[0] = '\0';
    
        close(sd);
    }
    close(request_sd);

    return 0;
}


