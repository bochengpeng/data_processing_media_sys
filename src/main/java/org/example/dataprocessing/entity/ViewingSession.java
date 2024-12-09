package org.example.dataprocessing.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ViewingSession
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sessionId;

    @ManyToOne
    @JoinColumn(name = "content_id", nullable = false)
    private Content content; // Establishing relationship with Content

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "history_id", nullable = false)
    private ViewingHistory viewingHistory; // Establish relationship with ViewingHistory

    // Constructors
    public ViewingSession() {
    }

    public ViewingSession(Content content, LocalDateTime startTime, LocalDateTime endTime, ViewingHistory viewingHistory) {
        this.content = content;
        this.startTime = startTime;
        this.endTime = endTime;
        this.viewingHistory = viewingHistory;
    }

    // Getters and Setters
    public Long getSessionId()
    {
        return sessionId;
    }

    public void setSessionId(Long sessionId)
    {
        this.sessionId = sessionId;
    }

    public Content getContent()
    {
        return content;
    }

    public void setContent(Content content)
    {
        this.content = content;
    }

    public LocalDateTime getStartTime()
    {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime)
    {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime()
    {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime)
    {
        this.endTime = endTime;
    }

    public ViewingHistory getViewingHistory()
    {
        return viewingHistory;
    }

    public void setViewingHistory(ViewingHistory viewingHistory)
    {
        this.viewingHistory = viewingHistory;
    }


}
