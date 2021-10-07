from flask_sqlalchemy import SQLAlchemy

db = SQLAlchemy()

class InterestRegion(db.Model):
  __tablename__ = "interest_region"

  id = db.Column(db.BigInteger, nullable=False, autoincrement=True, primary_key=True)
  user_id = db.Column(db.BigInteger)
  sigungu_id = db.Column(db.Integer)

  def __repr__(self):
    return f"[InterestRegion] ({self.id}, {self.user_id}, {self.sigungu_id})"

  def as_dict(self):
    return {x.name: getattr(self, x.name) for x in self.__table__.columns}