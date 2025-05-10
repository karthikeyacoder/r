import { useEffect } from 'react'
import '../styles/About.css'

function AboutPage() {
  const teamMembers = [
    {
      id: "2300030131",
      name: "CHEVURI JAHNAVI",
      type: "Team Lead",
      linkedIn: "https://www.linkedin.com/posts/jahnavi-chevuri-987b052a3_homigo-react-springboot-ugcPost-7295675794020872193-gplQ?utm_source=share&utm_medium=member_desktop&rcm=ACoAAELusFoBbf11Z5YN4ISsncJE9G57TklEYT8"
    },
    {
      id: "2300030326",
      name: "KODALI RAMYASRI",
      type: "Team Member",
      linkedIn: "https://www.linkedin.com/posts/jahnavi-chevuri-987b052a3_homigo-react-springboot-ugcPost-7295675794020872193-gplQ?utm_source=share&utm_medium=member_desktop&rcm=ACoAAELusFoBbf11Z5YN4ISsncJE9G57TklEYT8"
    },
    {
      id: "2300032330",
      name: "KARTHIKEYA VELIVELA",
      type: "Team Member",
      linkedIn: "https://www.linkedin.com/posts/jahnavi-chevuri-987b052a3_homigo-react-springboot-ugcPost-7295675794020872193-gplQ?utm_source=share&utm_medium=member_desktop&rcm=ACoAAELusFoBbf11Z5YN4ISsncJE9G57TklEYT8"
    }
  ]

  const handleMemberClick = (linkedIn) => {
    window.open(linkedIn, '_blank')
  }

  useEffect(() => {
    window.scrollTo(0, 0)
  }, [])

  return (
    <div className="about-page">
      <div className="about-container">
        <h1 className="about-title" data-aos="fade-up">About Our Team</h1>
        
        <div className="team-section" data-aos="fade-up" data-aos-delay="100">
          <div className="team-header">
            <div className="team-info">
              <p><strong>Course:</strong> 23SDCS12E - FULL STACK APPLICATION DEVELOPMENT</p>
              <p><strong>Team Name:</strong> FSAD-E-S24-11</p>
            </div>
          </div>
          
          <div className="team-members">
            {teamMembers.map((member, index) => (
              <div 
                key={member.id}
                className="team-member"
                onClick={() => handleMemberClick(member.linkedIn)}
                data-aos="fade-up"
                data-aos-delay={index * 100}
              >
                <div className="member-type">{member.type}</div>
                <div className="member-name">{member.name}</div>
                <div className="member-id">ID: {member.id}</div>
              </div>
            ))}
          </div>
        </div>
      </div>
    </div>
  )
}

export default AboutPage